package com.example.data.models

import com.example.business.models.*
import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    private val userId: Int,
    private val firstName: String,
    private val lastName: String,
    private val email: String,
    private var hashedPassword: String
) {

    fun getUserId(): Int {
        return userId
    }

    /**
     * Gets the first name property
     * @author Ömer Aynaci
     * @return the first name string
     */
    fun getFirstName(): String {
        return firstName
    }

    /**
     * Gets the last name property
     * @author Ömer Aynaci
     * @return the last name string
     */
    fun getLastName(): String {
        return lastName
    }

    /**
     * Gets the email property
     * @author Ömer Aynaci
     * @return the email string
     */
    fun getEmail(): String {
        return email
    }

    /**
     * Gets the password property
     * @author Ömer Aynaci
     * @return the password string
     */
    fun getHashedPassword(): String {
        return this.hashedPassword
    }
}