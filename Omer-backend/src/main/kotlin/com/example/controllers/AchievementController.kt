package com.example.controllers

import com.example.business.service.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class AchievementController (private val achievementService: AchievementService) {

    suspend fun getAllAchievements(request: ApplicationRequest,response: ApplicationResponse) {
        try {
            val achievements = achievementService.getAllAchievements()
            response.call.respond(HttpStatusCode.OK,achievements)
        } catch (error: IllegalArgumentException) {
            response.call.respond(HttpStatusCode.NotFound, mapOf("error" to "No Achievements Found"))
        }
    }
}