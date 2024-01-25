package com.example.exceptions

import kotlinx.serialization.Serializable

@Serializable
data class InvalidFirstNameError(val error: String) : IllegalArgumentException(error)

@Serializable
data class InvalidLastNameError(val error: String) : IllegalArgumentException(error)

@Serializable
data class InvalidEmailError(val error: String) : IllegalArgumentException(error)

@Serializable
data class InvalidPasswordError(val error: String) : IllegalArgumentException(error)