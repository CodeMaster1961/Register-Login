package com.example.authentication

import io.ktor.server.auth.*


data class AuthenticatedUser(
    val email: String,
    val firstName: String
) : Principal
