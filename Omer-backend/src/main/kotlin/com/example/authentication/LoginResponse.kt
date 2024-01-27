package com.example.authentication

import kotlinx.serialization.Serializable
import org.mindrot.jbcrypt.*

@Serializable
data class LoginResponse(val email: String, val password: String) {
    /**
     * matches the plain password with the hashed password
     * @author Ömer Aynaci
     * @param hashedPassword the hashed password that is stored
     * @return true if the password matches otherwise false
     */
    fun doesPasswordMatch(hashedPassword: String): Boolean {
        return BCrypt.checkpw(password,hashedPassword)
    }
}
