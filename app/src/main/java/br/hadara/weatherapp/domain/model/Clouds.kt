package br.hadara.weatherapp.domain.model


import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    val all: Int // 0
)