package com.example.business.service

import com.example.data.models.*
import com.example.data.repositories.*

class AchievementService (private val achievementRepository: AchievementRepository) {

    suspend fun getAllAchievements(): List<AchievementData> {
        return achievementRepository.getAllAchievements()
    }
}