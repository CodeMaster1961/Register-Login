package com.example.plugins


import com.example.business.service.*
import com.example.controllers.*
import com.example.routes.*
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
    routing {
        UserRoutes(userController).createUser(this)
    }


}
