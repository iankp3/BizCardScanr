package com.metrixia.bizcardscanr.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class CardEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String?,
    val email: String?,
    val phone: String?,
    val company: String?,
    val notes: String?
)
