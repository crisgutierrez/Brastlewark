package com.example.brastlewark.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        GnomeCacheEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun gnomeDao(): GnomeDao


    companion object{
        val DATABASE_NAME: String = "brastlewark_db"
    }
}