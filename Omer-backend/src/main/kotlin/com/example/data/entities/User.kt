package com.example.data.entities

import org.jetbrains.exposed.sql.*

/**
 * entity of the users
 * @author Ömer Aynaci
 */
object User : Table() {
    val userId = varchar("user_id", length = 128)
    val firstName = varchar("first_name", length = 128)
    val lastName = varchar("last_name", length = 128)
    val email = varchar("email", length = 128)
    val password = varchar("password", length = 128)

    override val primaryKey = PrimaryKey(userId)
}