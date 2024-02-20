package com.example.routes

import com.example.controllers.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class ShopItemRoutes (private val shopItemController: ShopItemController) {

    fun getAllShopItems(routing: Routing) {
        routing {
            get("/items") {
                val shopItems = shopItemController.getAllShopItems(call.request,call.response)
                call.respond(shopItems)
            }
        }
    }
}