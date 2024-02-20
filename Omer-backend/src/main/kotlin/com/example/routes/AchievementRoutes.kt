package com.example.routes

import com.example.controllers.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class AchievementRoutes(private val achievementController: AchievementController) {

    fun getAllAchievements(routing: Routing) {
        routing {
            get("/achievements") {
                val achievements = achievementController.getAllAchievements(call.request, call.response)
                call.respond(achievements)
            }
        }
    }
}