package br.hadara.weatherapp.domain.model


import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("base")
    val base: String, // stations
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("cod")
    val cod: Int, // 200
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("dt")
    val dt: Int, // 1485792967
    @SerializedName("id")
    val id: Int, // 1907296
    @SerializedName("main")
    val main: Main,
    @SerializedName("name")
    val name: String, // Tawarano
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind")
    val wind: Wind
)