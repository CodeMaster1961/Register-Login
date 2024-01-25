package com.example.data.repositoryImplementations

import com.example.business.models.*
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
            firstName = row[User.firstName],
            lastName = row[User.lastName],
            email = row[User.email],
            password = row[User.password]
        )
    }

    /**
     * registers a user
     * @author Ömer Aynaci
     */
    override suspend fun registerUser(user: UserData): Unit = databaseQuery {
        User.insert {
            it[firstName] = user.getFirstName()
            it[lastName] = user.getLastName()
            it[email] = user.getEmail()
            it[password] = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt())
        }
    }
}