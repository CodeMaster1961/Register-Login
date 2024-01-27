package com.example

import com.example.data.entities.*
import com.typesafe.config.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*

object DatabaseFactory {
    private val config: Config = ConfigFactory.load()

    private val database = Database.connect(
        url = config.getString("ktor.database.url"),
        driver = config.getString("ktor.database.driver"),
        user = config.getString("ktor.database.user"),
        password = config.getString("ktor.database.password")
    )

    /**
     * initializes the database and the tables
     * @author Ömer Aynaci
     */
    fun initializeDatabase() {
        transaction(database) {
            SchemaUtils.create(Users)
        }
    }
}