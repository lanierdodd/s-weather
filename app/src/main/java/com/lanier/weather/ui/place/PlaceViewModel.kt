package com.lanier.weather.ui.place

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lanier.weather.logic.model.Place
import com.lanier.weather.logic.model.PlaceX
import com.lanier.weather.logic.network.APIRequester
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaceViewModel : ViewModel() {

    private val _searchLiveData = MutableLiveData<List<PlaceX>>()
    val searchLiveData: LiveData<List<PlaceX>> = _searchLiveData

    val placeList = mutableListOf<PlaceX>()

    fun searchPlaces(
        place: String
    ) {
        APIRequester.placeService.searchPlaces(place).enqueue(object : Callback<Place> {
            override fun onResponse(p0: Call<Place>, p1: Response<Place>) {
                val mPlace = p1.body()
                if (mPlace != null) {
                    if (mPlace.status == "ok") {
                        val places = mPlace.places
                        _searchLiveData.postValue(places)
                    } else {
                        _searchLiveData.postValue(emptyList())
                        println("异常， $mPlace")
                    }
                }
            }

            override fun onFailure(p0: Call<Place>, p1: Throwable) {
                _searchLiveData.postValue(emptyList())
                println(p1)
            }

        })
    }


}