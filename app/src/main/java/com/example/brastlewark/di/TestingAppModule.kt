package com.example.brastlewark.di

import com.example.brastlewark.model.Gnome
import com.example.brastlewark.testsuport.FakeGnome
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestingAppModule {

    @Singleton
    @Provides
    fun provideTestGnome(): Gnome {
        return Gnome(
            id = 1,
            name = "Cristian Gutierrez",
            avatar = "https://avatars.githubusercontent.com/u/1564689?s=460&u=8eb991760ab6286324519f6a8abb759a2eae3f3f&v=4",
            age = 29,
            weight = 80.0,
            height = 1.79,
            hairColor = "Amarillo",
            professions = listOf("Ingeniero, Cocinero, Jardinero"),
            friends = listOf("Javier, Franco, Gian, Rodrigo"))
    }


}