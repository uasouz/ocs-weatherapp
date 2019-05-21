package br.hadara.weatherapp.domain

import br.hadara.weatherapp.util.Result
import retrofit2.Response

interface MainRepositoryInterface {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): Result<T>

    fun getWeatherByPosition(lat: Double, lon: Double)

    fun getWeatherById(id: Int)
}