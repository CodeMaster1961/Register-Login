package com.example.data.models

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    private val firstName: String,
    private val lastName: String,
    private val email: String,
    private val password: String
) {
    fun getFirstName(): String {
        return firstName
    }

    fun getLastName(): String {
        return lastName
    }

    fun getEmail(): String {
        return email
    }

    fun getPassword(): String {
        return password
    }
}