package com.example.brastlewark.network

import retrofit2.http.GET

interface GnomeService {

    @GET("rrafols/mobile_test/master/data.json")
    suspend fun get(): CityEntity

}