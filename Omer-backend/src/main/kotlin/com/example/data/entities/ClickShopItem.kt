package com.example.data.entities

import org.jetbrains.exposed.sql.*

object ClickShopItem : Table() {
    val id = integer("item_id").autoIncrement()
    val shopItemName = varchar("shop_item_name", length = 255)
    val shopItemCost = integer("shop_item_cost")
    val shopItemIncrease = integer("shop_item_increase")

    override val primaryKey = PrimaryKey(id)
}