package com.example.authentication

import com.example.business.models.*
import com.example.exceptions.*
import io.ktor.server.auth.*
import kotlinx.serialization.*
import org.mindrot.jbcrypt.*

@Serializable
data class LoginResponse(val email: String, val password: String) : Principal {

    @Contextual
    private val credentialsValidator = CredentialsValidator()

    /**
     * matches the plain password with the hashed password
     * @author Ömer Aynaci
     * @param hashedPassword the hashed password that is stored
     * @return true if the password matches otherwise false
     */
    fun doesPasswordMatch(hashedPassword: String?): Boolean {
        return BCrypt.checkpw(password, hashedPassword)
    }

    /**
     * validates if the email is valid or not
     * @author Ömer Aynaci
     * @throws InvalidEmailError if email is invalid otherwise not
     */
   private fun isEmailValid(): Boolean {
        return credentialsValidator.isEmailValid(email)
    }

    /**
     * validates the password
     * @author Ömer Aynaci
     * @throws InvalidPasswordError if password is invalid otherwise not
     */
   private fun isPasswordValid(): Boolean {
        return credentialsValidator.isPasswordValid(password)
    }

    fun validateCredentials(): Boolean {
        return isEmailValid() && isPasswordValid()
    }


    fun isUserCredentialsValid(storedPassword: String?): Boolean {
        if (storedPassword != null && doesPasswordMatch(storedPassword)) {
            return true
        } else {
            throw InvalidPasswordError("Invalid credentials")
        }
    }
}
