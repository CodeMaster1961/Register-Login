package com.example.business.service

import com.example.business.models.*
import com.example.data.models.*
import com.example.data.repositories.*

class GameService(private val gameRepository: GameRepository) {

    suspend fun getAllGames(): List<GameData> {
        return gameRepository.getAllGames()
    }

    suspend fun getGameById(gameId: Int): GameData? {
        return gameRepository.getGameById(gameId)
    }

    private fun GameBusiness.toGameData(): GameData {
        return GameData(getGameId(),getGameTitle(), getGameDescription(), getGameImage(), getGamePrice())
    }
}