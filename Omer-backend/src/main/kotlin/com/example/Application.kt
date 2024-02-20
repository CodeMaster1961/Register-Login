package com.example

import com.auth0.jwt.*
import com.auth0.jwt.algorithms.*
import com.example.authentication.*
import com.example.plugins.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import java.util.*

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
