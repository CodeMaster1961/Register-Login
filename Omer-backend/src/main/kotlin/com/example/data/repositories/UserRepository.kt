package com.example.data.repositories


import com.example.data.models.*

interface UserRepository {
    suspend fun registerUser(user: UserData)
    suspend fun getAllUsers(): List<UserData>
}