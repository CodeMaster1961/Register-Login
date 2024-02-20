package com.example.data.repositoryImplementations

import com.example.data.entities.*
import com.example.data.models.*
import com.example.data.repositories.*
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.*

class ClickShopRepositoryImplementation : ClickShopItemRepository {

    private suspend fun <T> databaseQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    private fun resultToRowItems(row: ResultRow): ClickShopItemData {
        return ClickShopItemData(
            id = row[ClickShopItem.id],
            shopItemName = row[ClickShopItem.shopItemName],
            shopItemCost = row[ClickShopItem.shopItemCost],
            shopItemIncrease = row[ClickShopItem.shopItemIncrease]
        )
    }

    override suspend fun getAllItems(): List<ClickShopItemData> = databaseQuery {
        ClickShopItem.selectAll().map(::resultToRowItems)
    }
}