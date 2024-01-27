package com.example.security

import com.example.authentication.*
import com.example.data.repositoryImplementations.*
import io.ktor.server.application.*
import io.ktor.server.auth.*

class Security {

    /**
     * Using basic authentication for authenticating a user with correct credentials
     * @author Ömer Aynaci
     */
    fun Application.authenticateUser() {
        authentication {
            basic("login") {
                realm = "Access to the '/' path"
                validate { credentials ->
                    val email = credentials.name
                    val password = credentials.password
                    val emailExists = UserRepositoryImplementation().getEmail(email)
                    val passwordMatch = UserRepositoryImplementation().getUserPasswordByEmail(email)
                    val user = LoginResponse(email, password)
                    if (emailExists && passwordMatch != null && user.doesPasswordMatch(passwordMatch)) {
                        UserIdPrincipal(credentials.name)
                    } else {
                        null
                    }
                }
            }
        }
    }
}