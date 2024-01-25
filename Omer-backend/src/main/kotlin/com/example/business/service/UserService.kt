package com.example.business.service

import com.example.business.models.*
import com.example.data.models.*
import com.example.data.repositories.*

class UserService(private val userRepository: UserRepository) {

    /**
     * creates the user if credentials are valid
     * @author Ömer Aynaci
     * @param user the user business model
     */
    suspend fun createUser(user: UserBusiness) {
        val firstNameValid = user.isFirstNameValid()
        val lastNameValid = user.isLastNameValid()
        val emailValid = user.isEmailValid()
        val passwordValid = user.isPasswordValid()
        if (firstNameValid && lastNameValid && emailValid && passwordValid) {
            userRepository.registerUser(user.toUserData())
        }
    }

    /**
     * converts the user business model to user data model
     * @author Ömer Aynaci
     */
    private fun UserBusiness.toUserData(): UserData {
        return UserData(firstName, lastName, email, password)
    }

}