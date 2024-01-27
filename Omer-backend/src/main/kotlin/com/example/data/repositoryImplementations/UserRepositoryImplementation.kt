package com.example.data.repositoryImplementations

import com.example.*
import com.example.data.entities.*
import com.example.data.models.*
import com.example.data.repositories.*
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.*
import org.mindrot.jbcrypt.BCrypt

class UserRepositoryImplementation : UserRepository {

    private suspend fun <T> databaseQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }


    private fun resultToRowUser(row: ResultRow): UserData {
        return UserData(
            firstName = row[Users.firstName],
            lastName = row[Users.lastName],
            email = row[Users.email],
            password = row[Users.password]
        )
    }

    /**
     * registers a user
     * @author Ömer Aynaci
     */
    override suspend fun registerUser(user: UserData): Unit = databaseQuery {
        Users.insert {
            it[firstName] = user.getFirstName()
            it[lastName] = user.getLastName()
            it[email] = user.getEmail()
            it[password] = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt())
        }
    }

    suspend fun doesEmailExists(email: String?): Boolean = databaseQuery {
        if (email != null) {
            val result = Users.select { Users.email eq email }.singleOrNull()
            result != null
        } else {
            throw IllegalArgumentException("Email doesn't exists")
        }
    }

    suspend fun getEmail(email: String?): Boolean = databaseQuery {
        if (email != null) {
            val result = Users.select { Users.email eq email }.singleOrNull()
            result != null
        } else {
            throw IllegalArgumentException("Email cannot be null")
        }
    }

    suspend fun getUserPasswordByEmail(email: String) = databaseQuery {
        val existingUser = Users.select { Users.email eq email }
            .map(::resultToRowUser)
            .singleOrNull()
        if (existingUser != null) {
            return@databaseQuery existingUser.getPassword()
        } else {
            return@databaseQuery null
        }
    }
}