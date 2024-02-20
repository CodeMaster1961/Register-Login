package com.example.routes

import com.auth0.jwt.*
import com.auth0.jwt.algorithms.*
import com.example.authentication.*
import com.example.controllers.*
import com.example.security.*
import com.typesafe.config.*
import io.ktor.client.engine.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.nio.file.attribute.UserPrincipal
import java.util.*

class UserRoutes(private val userController: UserController) {

    private val config: Config = ConfigFactory.load()
    private val audience = config.getString("ktor.jwt.audience")
    private val issuer = config.getString("ktor.jwt.issuer")
    private val secret = config.getString("ktor.jwt.secret")

    /**
     * route for creating an account
     * @author Ömer Aynaci
     * @param routing the routing block where you can do CRUD operations
     */
    fun createUser(routing: Routing) {
        routing {
            post("/users") {
                userController.createUser(call.request, call.response)
            }
        }
    }

    fun getUsers(routing: Routing) {
        routing {
            get("/getUsers") {
                val user = userController.getAllUsers(call.request, call.response)
                call.respond(user)
            }
        }
    }

    /**
     * route for logging in a user
     * @author Ömer Aynaci
     * @param routing the routing block where you can do CRUD operations
     */
    fun login(routing: Routing) {
        routing {
            authenticate("login") {
                post("/login") {
                    val user = call.receive<LoginResponse>()
                    val token = JWT.create()
                        .withAudience(audience)
                        .withIssuer(issuer)
                        .withClaim("email", user.email)
                        .withExpiresAt(Date(System.currentTimeMillis() + 60000))
                        .sign(Algorithm.HMAC256(secret))
                    val cookie = Cookie(name = "session-id", value = token)

                    val principal = call.principal<UserIdPrincipal>()
                    if (principal != null) {
                        call.response.cookies.append(cookie)
                        call.respond(mapOf("message" to "Logged in"))
                    } else {
                        call.respond(HttpStatusCode.Unauthorized, "Invalid credentials")
                    }
                }
            }
        }
    }

    /**
     * route for getting authenticated user
     * @author Ömer Aynaci
     * @param routing the routing block where you can do CRUD operations
     */
    fun getAuthenticatedUser(routing: Routing) {
        routing {
            authenticate("auth-jwt") {
            get("/hello") {
                val principal = call.principal<MyJwtPrincipal>()
//                    val username = principal!!.payload.getClaim("email").asString()
                val firstName = principal?.firstName
//                    val expiresAt = principal.expiresAt?.time?.minus(System.currentTimeMillis())
//                    call.respond(mapOf("message" to "Hello, $username! Token is expired at $expiresAt ms."))
                call.respond(mapOf("message" to "Hello, $firstName!"))
            }
            }
        }
    }
}