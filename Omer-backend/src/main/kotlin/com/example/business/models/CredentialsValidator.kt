package com.example.business.models

import com.example.exceptions.*
import kotlinx.serialization.*


@Serializable
class CredentialsValidator {

    @Contextual
    private val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#\$%^&+=])([A-Za-z\\d@#\$%^&+=]){8,32}\$")

    @Contextual
    private val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")


    fun isFirstNameValid(firstName: String): Boolean {
        if (firstName.length in 1..10) {
            return true
        } else {
            throw InvalidFirstNameError("First name is invalid")
        }
    }

    fun isLastNameValid(lastName: String): Boolean {
        if (lastName.length in 1..10) {
            return true
        } else {
            throw InvalidLastNameError("Last name is invalid")
        }
    }

    fun isEmailValid(email: String): Boolean {
        if (email.matches(emailRegex)) {
            return true
        } else {
            throw InvalidEmailError("Email is Invalid")
        }
    }

    fun isPasswordValid(password: String): Boolean {
        if (password.matches(passwordRegex)) {
            return true
        } else {
            throw InvalidPasswordError("Password is Invalid")
        }
    }
}