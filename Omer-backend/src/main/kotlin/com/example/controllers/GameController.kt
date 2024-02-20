package com.example.controllers

import com.example.business.service.*
import io.ktor.client.engine.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class GameController(private val gameService: GameService) {

    suspend fun getAllGames(request: ApplicationRequest, response: ApplicationResponse) {
        try {
            val games = gameService.getAllGames()
            response.call.respond(HttpStatusCode.OK, games)
        } catch (error: IllegalArgumentException) {
            response.call.respond(HttpStatusCode.NotFound, mapOf("error" to "No games found"))
        }
    }

    suspend fun getGameById(request: ApplicationRequest, response: ApplicationResponse) {
        try {
            val gameId = request.call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("No Id found")
            val game = gameService.getGameById(gameId)
            if (game != null) {
                response.call.respond(HttpStatusCode.OK, game)
            } else {
                response.call.respond(HttpStatusCode.NotFound, mapOf("error" to "No game found with id: $gameId"))
            }
        } catch (error: IllegalArgumentException) {
            response.call.respond(HttpStatusCode.NotFound, mapOf("error" to "No game found"))
        }
    }
}