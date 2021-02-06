package com.example.brastlewark.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GnomeDao {

    //INSERT or UPDATE if exists
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGnome(gnomeCacheEntity: GnomeCacheEntity)


    //GET
    @Query("SELECT * FROM ${GnomeCacheEntity.TABLE_NAME}")
    suspend fun getAllGnomes(): List<GnomeCacheEntity>

    @Query("SELECT * FROM ${GnomeCacheEntity.TABLE_NAME} WHERE id LIKE :id")
    suspend fun getGnomeById(id: Int): GnomeCacheEntity


    //DELETE
    @Query("DELETE FROM ${GnomeCacheEntity.TABLE_NAME} WHERE id LIKE :id")
    suspend fun deleteGnomeById(id: Int)

    @Query("DELETE FROM ${GnomeCacheEntity.TABLE_NAME}")
    suspend fun clearGnomeTable()

}