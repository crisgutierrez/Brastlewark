package com.example.brastlewark.repository

import com.example.brastlewark.model.Gnome
import com.example.brastlewark.network.GnomeService
import com.example.brastlewark.network.NetworkMapper
import com.example.brastlewark.room.GnomeCacheMapper
import com.example.brastlewark.room.GnomeDao
import com.example.brastlewark.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GnomeRepository
constructor(
    private val gnomeDao: GnomeDao,
    private val gnomeService: GnomeService,
    private val cacheMapper: GnomeCacheMapper,
    private val networkMapper: NetworkMapper
){

    suspend fun getGnomes(): Flow<DataState<List<Gnome>>> = flow {
        emit(DataState.InProgress())

        try {
            val networkGnomes = gnomeService.get().city
            val gnomes = networkMapper.mapFromEntityList(networkGnomes)
            for (gnome in gnomes) {
                gnomeDao.insertGnome(cacheMapper.mapToEntity(gnome))
            }
            val cacheGnomes = gnomeDao.getAllGnomes()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cacheGnomes)))
        }catch (e: Exception) {
            emit(DataState.Failure(e))
        }
    }

}