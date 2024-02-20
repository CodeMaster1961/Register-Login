package com.example.business.models

import com.example.exceptions.*
import kotlinx.serialization.*
import org.mindrot.jbcrypt.BCrypt

@Serializable
class UserBusiness(
    val userId: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
) {

    @Contextual
    private val credentialsValidator = CredentialsValidator()

    /**
     * validates if first name is valid
     * @author Ömer Aynaci
     */
    fun isFirstNameValid(): Boolean {
        return credentialsValidator.isFirstNameValid(firstName)
    }

    /**
     * validates the last name length
     * @author Ömer Aynaci
     * @throws InvalidLastNameError if last name is invalid otherwise not
     */
    fun isLastNameValid(): Boolean {
       return credentialsValidator.isLastNameValid(lastName)
    }

    /**
     * validates if the email is valid or not
     * @author Ömer Aynaci
     * @throws InvalidEmailError if email is invalid otherwise not
     */
    fun isEmailValid(): Boolean {
        return credentialsValidator.isEmailValid(email)
    }

    /**
     * validates the password
     * @author Ömer Aynaci
     * @throws InvalidPasswordError if password is invalid otherwise not
     */
    fun isPasswordValid(): Boolean {
        return credentialsValidator.isPasswordValid(password)
    }
}