package com.example.data.models

import kotlinx.serialization.Serializable

@Serializable
data class GameData(
    val gameId: Int,
    val gameTitle: String,
    val gameDescription: String,
    val gameImage: String,
    val gamePrice: Double
)