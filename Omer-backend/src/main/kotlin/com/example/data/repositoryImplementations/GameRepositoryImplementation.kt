package com.example.data.repositoryImplementations

import com.example.data.entities.*
import com.example.data.models.*
import com.example.data.repositories.*
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.*

class GameRepositoryImplementation : GameRepository {

    private suspend fun <T> databaseQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    private fun resultToGameRow(row: ResultRow): GameData {
        return GameData(
            gameId = row[Game.gameId],
            gameTitle = row[Game.gameTitle],
            gameDescription = row[Game.gameDescription],
            gameImage = row[Game.gameImage],
            gamePrice = row[Game.gamePrice]
        )
    }

    /**
     * gets a list of games
     * @author Ömer Aynaci
     */
    override suspend fun getAllGames(): List<GameData> = databaseQuery {
        Game.selectAll().map(::resultToGameRow)
    }

    override suspend fun getGameById(gameId: Int): GameData? = databaseQuery {
        Game.select { Game.gameId eq gameId }
            .map(::resultToGameRow)
            .singleOrNull()
    }
}