package br.hadara.weatherapp.domain.model


import com.google.gson.annotations.SerializedName

data class Coord(
    @SerializedName("lat")
    val lat: Double, // 35.02
    @SerializedName("lon")
    val lon: Double // 139.01
)