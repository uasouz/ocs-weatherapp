package br.hadara.weatherapp.domain.model


import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("deg")
    val deg: Int, // 311
    @SerializedName("speed")
    val speed: Double // 5.52
)