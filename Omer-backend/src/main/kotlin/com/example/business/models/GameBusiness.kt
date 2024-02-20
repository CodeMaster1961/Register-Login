package com.example.business.models

class GameBusiness(
    private val gameId: Int,
    private val gameTitle: String,
    private val gameDescription: String,
    private val gameImage: String,
    private val gamePrice: Double
) {

    fun getGameId(): Int {
        return gameId
    }

    fun getGameTitle(): String {
        return gameTitle
    }

    fun getGameDescription(): String {
        return gameDescription
    }

    fun getGameImage(): String {
        return gameImage
    }

    fun getGamePrice(): Double {
        return gamePrice
    }
}