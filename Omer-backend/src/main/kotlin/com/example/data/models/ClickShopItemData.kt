package com.example.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ClickShopItemData(
    val id: Int,
    val shopItemName: String,
    val shopItemCost: Int,
    val shopItemIncrease: Int
)