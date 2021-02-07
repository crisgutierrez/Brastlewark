package com.example.brastlewark.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = GnomeCacheEntity.TABLE_NAME)
data class GnomeCacheEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")         val id: Int,
    @ColumnInfo(name = "gnomeJsonObject") val gnomeJsonObject: String
    ) {

    companion object {
        const val TABLE_NAME = "gnome"
    }
}
