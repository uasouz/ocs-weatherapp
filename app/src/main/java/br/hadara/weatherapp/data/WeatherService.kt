package br.hadara.weatherapp.data

import br.hadara.weatherapp.data.Constants.apiKey
import br.hadara.weatherapp.domain.model.WeatherResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")//Query lat,Query lon
    fun WeatherByPosition(
        @Query("lat") lat: Double, @Query("lon") lon: Double,
        @Query("appid") Key: String = apiKey
    ): Deferred<Response<WeatherResponse>>


    @GET("weather")//Query id
    fun WeatherById(
        @Query("id") id: Int,
        @Query("appid") Key: String = apiKey
    ): Deferred<Response<WeatherResponse>>


    @GET("weather")//Query id
    fun WeatherByName(
        @Query("q") name: String,
        @Query("appid") Key: String = apiKey
    ): Deferred<Response<WeatherResponse>>

}