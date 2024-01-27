package com.example.plugins


import com.example.business.service.*
import com.example.controllers.*
import com.example.routes.*
import com.example.security.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.*


/**
 * the routes of the resources
 * @author Ömer Aynaci
 */
fun Application.configureRouting() {
    val userService by inject<UserService>()
    val userController = UserController(userService)
    Security().apply { authenticateUser() }
    routing {
        UserRoutes(userController).createUser(this)
        UserRoutes(userController).login(this)
    }
}
