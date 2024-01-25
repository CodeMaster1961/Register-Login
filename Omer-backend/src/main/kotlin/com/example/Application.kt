package com.example

import com.example.plugins.*
import io.ktor.server.application.*

/**
 * The main point of the application
 * @author Ömer Aynaci
 */
fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

/**
 * configurations of the application
 * @author Ömer Aynaci
 */
fun Application.module() {
    DatabaseFactory.initializeDatabase()
    configureKoin()
    configureSerialization()
    configureHTTP()
    configureRouting()
}
