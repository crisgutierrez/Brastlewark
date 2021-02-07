package com.example.brastlewark.network

import retrofit2.http.GET

interface GnomeService {

    companion object {
        private const val CITY_PATH = "rrafols/mobile_test/master/data.json"
    }

    @GET(CITY_PATH)
    suspend fun get(): CityEntity

}