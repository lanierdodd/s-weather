package com.lanier.weather.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object APIRequester {
    lateinit var placeService: PlaceService

    fun init() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.caiyunapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        placeService = retrofit.create<PlaceService>()
    }

}