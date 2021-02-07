package com.example.brastlewark.repository

import com.example.brastlewark.model.Gnome
import com.example.brastlewark.network.GnomeService
import com.example.brastlewark.network.NetworkMapper
import com.example.brastlewark.room.GnomeCacheMapper
import com.example.brastlewark.room.GnomeDao
import com.example.brastlewark.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GnomeRepository
constructor(
    private val gnomeDao: GnomeDao,
    private val gnomeService: GnomeService,
    private val cacheMapper: GnomeCacheMapper,
    private val networkMapper: NetworkMapper
){

    /**
     * First we check if have data stored locally, if we don't  we request it to the BE, if we have return it right away and
     * we check if there is new data in the BE if there is we update the list and notify the viewModel
     * otherwise we do nothing (the viewModel already have the update data).
     */
    suspend fun getGnomes(): Flow<DataState<List<Gnome>>> = flow {
        emit(DataState.InProgress())

        try {
            var cacheGnomes = gnomeDao.getAllGnomes()
            if (!cacheGnomes.isNullOrEmpty()) {
                emit(DataState.Cached(cacheMapper.mapFromEntityList(cacheGnomes)))
            }
            val networkGnomes = gnomeService.get().city
            val gnomes = networkMapper.mapFromEntityList(networkGnomes)
            if (gnomes.size != cacheGnomes.size) {
                for (gnome in gnomes) {
                    gnomeDao.insertGnome(cacheMapper.mapToEntity(gnome))
                }
                cacheGnomes = gnomeDao.getAllGnomes()
                emit(DataState.Success(cacheMapper.mapFromEntityList(cacheGnomes)))
            }
        } catch (e: Exception) {
            emit(DataState.Failure(e))
        }
    }

}