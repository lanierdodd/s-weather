package com.lanier.weather.logic.network

import com.lanier.weather.BaseApp
import com.lanier.weather.logic.model.Place
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {
    @GET("v2/place?token=${BaseApp.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query : String) : Call<Place>
}