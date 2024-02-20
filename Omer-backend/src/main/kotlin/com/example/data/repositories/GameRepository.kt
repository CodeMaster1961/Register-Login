package com.example.data.repositories

import com.example.data.models.*

interface GameRepository {
    suspend fun getAllGames(): List<GameData>
    suspend fun getGameById(gameId: Int): GameData?
}