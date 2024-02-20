package com.example.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.*
import com.example.authentication.*
import com.example.data.repositoryImplementations.*
import com.example.exceptions.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

data class MyJwtPrincipal(
    val email: String,
    val firstName: String,
) : Principal

class Security {

    /**
     * Using basic authentication for authenticating a user with correct credentials
     * @author Ömer Aynaci
     */
    fun Application.authenticateUserBasic() {
        authentication {
            basic("login") {
                realm = "Access to the '/' path"
                validate { credentials ->
                    val email = credentials.name
                    val password = credentials.password
                    val user = LoginResponse(email, password)
                    authenticatedUser(credentials, user, response)
                }
            }
        }
    }

    fun Application.authenticateUser() {
        val secret = environment.config.property("ktor.jwt.secret").getString()
        val issuer = environment.config.property("ktor.jwt.issuer").getString()
        val audience = environment.config.property("ktor.jwt.audience").getString()
        val myRealm = environment.config.property("ktor.jwt.realm").getString()
        install(Authentication) {
            jwt("auth-jwt") {
                realm = myRealm
                verifier(
                    JWT
                        .require(Algorithm.HMAC256(secret))
                        .withAudience(audience)
                        .withIssuer(issuer)
                        .build()
                )
                validate { credential ->
                    val email = credential.payload.getClaim("email").asString()
                    val password = credential.payload.getClaim("password").asString()

                    if (email != null) {
                        val firstName = UserRepositoryImplementation().getFirstNameByEmail(email)
                        MyJwtPrincipal(email, firstName)
                    } else {
                        null
                    }
                }
                challenge { defaultScheme, realm ->
                    call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
                }
            }

            basic("login") {
                realm = "Access to the '/' path"
                validate { credentials ->
                    val email = credentials.name
                    val password = credentials.password
                    val user = LoginResponse(email, password)
                    authenticatedUser(credentials, user, response)
                }
            }
        }
    }


    private suspend fun authenticatedUser(
        credential: UserPasswordCredential, user: LoginResponse, response: ApplicationResponse
    ): Principal? {
        val emailExists = UserRepositoryImplementation().doesEmailExists(user.email)
        val passwordMatch = UserRepositoryImplementation().getUserIdByEmail(user.email)


        if (passwordMatch != null) {
            if (emailExists && user.doesPasswordMatch(passwordMatch.first)) {
                return UserIdPrincipal(user.email)
            } else {
                checkEmail(user.email, response)
                validateCredentials(user, response)
                return null
            }
        }
        return null
    }

    private suspend fun checkEmail(email: String, response: ApplicationResponse) {
        val emailExists = UserRepositoryImplementation().getEmail(email)
        if (!emailExists) {
            response.call.respond(HttpStatusCode.Unauthorized, InvalidEmailError("Invalid credentials"))
        }
    }

    private suspend fun validateCredentials(user: LoginResponse, response: ApplicationResponse) {
        val password = UserRepositoryImplementation().getUserIdByEmail(user.email)
        try {
            password != null && user.validateCredentials() && user.doesPasswordMatch(password.first)
        } catch (error: InvalidPasswordError) {
            response.call.respond(HttpStatusCode.Unauthorized, InvalidPasswordError(error.message!!))
        }
    }
}