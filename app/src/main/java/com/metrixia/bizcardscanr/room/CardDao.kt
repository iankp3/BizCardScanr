package com.metrixia.bizcardscanr.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CardDao {
    @Query("SELECT * FROM cards ORDER BY id DESC")
    fun getAll(): List<CardEntity>

    @Insert
    fun insertCard(card: CardEntity)

    @Delete
    fun deleteCard(card: CardEntity)
}
