package com.example.routes

import com.example.controllers.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class UserRoutes(private val userController: UserController) {

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

    /**
     * route for logging in a user
     * @author Ömer Aynaci
     * @param routing the routing block where you can do CRUD operations
     */
    fun login(routing: Routing) {
        routing {
            authenticate("login") {
                post("/login") {
                    call.respond(mapOf("message" to "Successfully logged in"))
                }
            }
        }
    }
}