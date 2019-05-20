package br.hadara.weatherapp.domain.model


import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("grnd_level")
    val grndLevel: Double, // 1013.75
    @SerializedName("humidity")
    val humidity: Int, // 100
    @SerializedName("pressure")
    val pressure: Double, // 1013.75
    @SerializedName("sea_level")
    val seaLevel: Double, // 1023.22
    @SerializedName("temp")
    val temp: Double, // 285.514
    @SerializedName("temp_max")
    val tempMax: Double, // 285.514
    @SerializedName("temp_min")
    val tempMin: Double // 285.514
)