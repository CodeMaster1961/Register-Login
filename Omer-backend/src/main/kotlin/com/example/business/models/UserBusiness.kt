package com.example.business.models

import com.example.exceptions.*
import kotlinx.serialization.*
import org.mindrot.jbcrypt.BCrypt

@Serializable
class UserBusiness(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
) {

    @Contextual
    private val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#\$%^&+=])([A-Za-z\\d@#\$%^&+=]){8,32}\$")

    @Contextual
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

    /**
     * validates the last name length
     * @author Ömer Aynaci
     * @throws InvalidLastNameError if last name is invalid otherwise not
     */
    fun isLastNameValid(): Boolean {
        if (lastName.length in 1..10) {
            return true
        } else {
            throw InvalidLastNameError("Last name is invalid")
        }
    }

    /**
     * validates if the email is valid or not
     * @author Ömer Aynaci
     * @throws InvalidEmailError if email is invalid otherwise not
     */
    fun isEmailValid(): Boolean {
        if (email.matches(emailRegex)) {
            return true
        } else {
            throw InvalidEmailError("The given email is invalid")
        }
    }

    /**
     * validates the password
     * @author Ömer Aynaci
     * @throws InvalidPasswordError if password is invalid otherwise not
     */
    fun isPasswordValid(): Boolean {
        if (password.matches(passwordRegex)) {
            return true
        } else {
            throw InvalidPasswordError("The given password is invalid")
        }
    }
}