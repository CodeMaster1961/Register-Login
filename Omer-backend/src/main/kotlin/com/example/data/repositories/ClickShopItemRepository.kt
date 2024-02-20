package com.example.data.repositories

import com.example.data.models.*

interface ClickShopItemRepository {
    suspend fun getAllItems(): List<ClickShopItemData>
}