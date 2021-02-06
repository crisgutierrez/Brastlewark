package com.example.brastlewark.di

import com.example.brastlewark.network.GnomeService
import com.example.brastlewark.network.NetworkMapper
import com.example.brastlewark.repository.GnomeRepository
import com.example.brastlewark.room.GnomeCacheMapper
import com.example.brastlewark.room.GnomeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideGnomeRepository(
        gnomeDao: GnomeDao,
        gnomeService: GnomeService,
        cacheMapper: GnomeCacheMapper,
        networkMapper: NetworkMapper
    ) : GnomeRepository {
        return GnomeRepository(gnomeDao, gnomeService, cacheMapper, networkMapper)
    }

}