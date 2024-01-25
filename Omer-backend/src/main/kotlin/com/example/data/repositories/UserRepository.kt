package com.example.data.repositories

import com.example.business.models.*
import com.example.data.models.*

interface UserRepository {
    suspend fun registerUser(user: UserData): UserData
}