package com.lanier.weather.logic.model

data class Place(
    val places: List<PlaceX>,
    val query: String,
    val status: String
)

data class PlaceX(
    val formatted_address: String,
    val id: String,
    val location: Location,
    val name: String,
    val place_id: String
)

data class Location(
    val lat: Double,
    val lng: Double
)