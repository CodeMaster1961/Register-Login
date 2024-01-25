package com.example.business.models

import com.example.exceptions.*

class UserBusiness(
    private val firstName: String,
    private val lastName: String,
    private val email: String,
    private val password: String
) {

    private val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#\$%^&+=])([A-Za-z\\d@#\$%^&+=]){8,32}\$")
    private val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
    /**
     * validates if first name is valid
     * @author Ömer Aynaci
     * @throws InvalidFirstNameError if first name is invalid throw not error
     */
    fun isFirstNameValid(): Boolean {
        if (firstName.length in 1..10) {
            return true
        } else {
            throw InvalidFirstNameError("First name is invalid")
        }
    }

    fun isLastNameValid(): Boolean {
        if (lastName.length in 1..10) {
            return true
        } else {
            throw InvalidLastNameError("Last name is invalid")
        }
    }

    fun isEmailValid(): Boolean {
        if (email.matches(emailRegex)) {
            return true
        } else {
            throw InvalidEmailError("The given email is invalid")
        }
    }

    fun isPasswordValid(): Boolean {
        if (password.matches(passwordRegex)) {
            return true
        } else {
            throw InvalidPasswordError("The given password is invalid")
        }
    }
}