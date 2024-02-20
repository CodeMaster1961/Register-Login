package com.example.data.repositoryImplementations

import com.example.data.entities.*
import com.example.data.models.*
import com.example.data.repositories.*
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.*

class AchievementRepositoryImplementation : AchievementRepository {

    private suspend fun <T> databaseQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    private fun resultToRowAchievements(row: ResultRow): AchievementData {
        return AchievementData(
            achievementId = row[Achievements.achievementId],
            achievementDescription = row[Achievements.achievementDescription],
            achievementImage = row[Achievements.achievementImage]
        )
    }

    override suspend fun getAllAchievements(): List<AchievementData> = databaseQuery {
        Achievements.selectAll().map(::resultToRowAchievements)
    }
}