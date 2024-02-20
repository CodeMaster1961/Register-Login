package com.example.data.models

import kotlinx.serialization.Serializable

@Serializable
data class AchievementData(
    val achievementId: Int,
    val achievementDescription: String,
    val achievementImage: String
)
