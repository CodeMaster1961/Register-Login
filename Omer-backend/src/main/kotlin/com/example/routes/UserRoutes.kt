package com.example.routes

import com.example.controllers.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

class UserRoutes(private val userController: UserController) {

    fun createUser(routing: Routing) {
        routing {
            post("/users") {
                userController.createUser(call.request, call.response)
            }
        }
    }
}