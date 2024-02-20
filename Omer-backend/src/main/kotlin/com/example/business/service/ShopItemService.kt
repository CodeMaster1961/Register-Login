package com.example.business.service

import com.example.data.models.*
import com.example.data.repositories.*

class ShopItemService (private val clickShopItemRepository: ClickShopItemRepository) {

    suspend fun getAllShopItems(): List<ClickShopItemData> {
        return clickShopItemRepository.getAllItems()
    }
}