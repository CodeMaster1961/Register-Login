package com.example.controllers

import com.example.business.service.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class ShopItemController(private val shopItemService: ShopItemService) {

    suspend fun getAllShopItems(request: ApplicationRequest, response: ApplicationResponse) {
        try {
            val shopItems = shopItemService.getAllShopItems()
            response.call.respond(HttpStatusCode.OK, shopItems)
        } catch (error: IllegalArgumentException) {
            response.call.respond(HttpStatusCode.NotFound, mapOf("error" to "No items found"))
        }
    }
}