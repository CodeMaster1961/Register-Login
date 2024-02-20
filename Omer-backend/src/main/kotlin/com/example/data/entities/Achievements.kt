package com.example.data.entities

import org.jetbrains.exposed.sql.*

object Achievements : Table() {
    val achievementId = integer("achievement_id").autoIncrement()
    val achievementDescription = varchar("achievement_description", length = 255)
    val achievementImage = varchar("achievement_Image", length = 500)

    override val primaryKey = PrimaryKey(achievementId)
}