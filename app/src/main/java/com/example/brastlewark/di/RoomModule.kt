package com.example.brastlewark.di

import android.content.Context
import androidx.room.Room
import com.example.brastlewark.room.AppDatabase
import com.example.brastlewark.room.GnomeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideGnomeDAO(appDatabase: AppDatabase): GnomeDao {
        return appDatabase.gnomeDao()
    }

}