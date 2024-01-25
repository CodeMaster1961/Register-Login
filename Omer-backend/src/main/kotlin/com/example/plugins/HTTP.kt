package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.hsts.*

/**
 * configures the HTTP Strict Transport Security
 * @author Ömer Aynaci
 */
fun Application.configureHTTP() {
    install(HSTS) {
        includeSubDomains = true
        maxAgeInSeconds = 316000
    }
}
