package com.example.data.repositories

import com.example.data.models.*

interface AchievementRepository {
    suspend fun getAllAchievements(): List<AchievementData>
}