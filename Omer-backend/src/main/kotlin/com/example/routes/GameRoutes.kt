package com.example.routes

import com.example.controllers.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class GameRoutes (private val gameController: GameController) {

    fun getAllGames(routing: Routing) {
        routing {
            get("/games") {
               val games = gameController.getAllGames(call.request,call.response)
                call.respond(games)
            }
        }
    }

    fun getGameById(routing: Routing) {
        routing {
            get("/games/{id}") {
                val game = gameController.getGameById(call.request,call.response)
                call.respond(game)
            }
        }
    }
}