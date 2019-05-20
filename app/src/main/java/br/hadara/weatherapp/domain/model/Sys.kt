package br.hadara.weatherapp.domain.model


import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("country")
    val country: String, // JP
    @SerializedName("message")
    val message: Double, // 0.0025
    @SerializedName("sunrise")
    val sunrise: Int, // 1485726240
    @SerializedName("sunset")
    val sunset: Int // 1485763863
)