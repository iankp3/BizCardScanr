package com.metrixia.bizcardscanr.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CardEntity::class], version = 2, exportSchema = false)
abstract class CardDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao

    companion object {
        @Volatile private var INSTANCE: CardDatabase? = null

        fun getInstance(context: Context): CardDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    CardDatabase::class.java,
                    "card_db"
                )
                    // If schema changes, clear DB (for dev/testing)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries() // ⚠️ for demo; better use coroutine/Flow
                    .build().also { INSTANCE = it }
            }
        }
    }
}
