package com.example.plugins

import com.example.modules.*
import io.ktor.server.application.*
import org.koin.ktor.plugin.*
import org.koin.logger.*

/**
 * configures koin
 * @author Ömer Aynaci
 */
fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(appModule)
    }
}