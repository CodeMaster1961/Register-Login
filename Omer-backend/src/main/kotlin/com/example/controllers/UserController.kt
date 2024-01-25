package com.example.controllers

import com.example.business.models.*
import com.example.business.service.*
import com.example.exceptions.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class UserController(private val userService: UserService) {

    /**
     * creates the user
     * @author Ömer Aynaci
     * @param request handles the requests
     * @param response handles the responses
     */
    suspend fun createUser(request: ApplicationRequest, response: ApplicationResponse) {
        try {
            val user = request.call.receive<UserBusiness>()
            userService.createUser(user)
            response.call.respond(HttpStatusCode.Created, mapOf("message" to "User Registered"))
        } catch (error: InvalidFirstNameError) {
            response.call.respond(HttpStatusCode.BadRequest, InvalidFirstNameError(error.message!!))
        } catch (error: InvalidLastNameError) {
            response.call.respond(HttpStatusCode.BadRequest, InvalidLastNameError(error.message!!))
        } catch (error: InvalidEmailError) {
            response.call.respond(HttpStatusCode.BadRequest, InvalidEmailError(error.message!!))
        } catch (error: InvalidPasswordError) {
            response.call.respond(HttpStatusCode.BadRequest, InvalidPasswordError(error.message!!))
        }
    }
}