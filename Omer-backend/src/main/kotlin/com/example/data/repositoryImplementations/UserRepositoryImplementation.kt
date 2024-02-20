package com.example.data.repositoryImplementations

import com.example.*
import com.example.authentication.*
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
            userId = row[Users.userId],
            firstName = row[Users.firstName],
            lastName = row[Users.lastName],
            email = row[Users.email],
            hashedPassword = row[Users.password]
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
            it[password] = BCrypt.hashpw(user.getHashedPassword(), BCrypt.gensalt())
        }
    }

    override suspend fun getAllUsers(): List<UserData> = databaseQuery {
        Users.selectAll().map(::resultToRowUser)
    }

    suspend fun getFirstNameByEmail(email: String?): String = databaseQuery {
        if (email != null) {
            val result = Users.select { Users.email eq email }.singleOrNull()
            result?.let {
                return@databaseQuery result[Users.firstName]
            }
        }
        ""
    }

    suspend fun doesEmailExists(email: String?): Boolean = databaseQuery {
        if (email != null) {
            val result = Users.select { Users.email eq email }.singleOrNull()
            result != null
        } else {
            return@databaseQuery false
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

    suspend fun getUserIdByEmail(email: String): Pair<String,Int>? = databaseQuery {
        val existingUser = Users.select { Users.email eq email }
            .map(::resultToRowUser)
            .singleOrNull()
        if (existingUser == null) {
            return@databaseQuery null
        } else {
            return@databaseQuery Pair<String,Int>(existingUser.getHashedPassword(),existingUser.getUserId())
        }
    }
}