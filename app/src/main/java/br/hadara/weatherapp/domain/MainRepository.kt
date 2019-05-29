package br.hadara.weatherapp.domain

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.hadara.weatherapp.data.WeatherService
import br.hadara.weatherapp.domain.model.WeatherResponse
import br.hadara.weatherapp.util.Outcome
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.CoroutineContext
import br.hadara.weatherapp.util.Result
import br.hadara.weatherapp.util.success
import kotlinx.coroutines.*
import javax.inject.Inject

class MainRepository @Inject constructor(val apiService: WeatherService) : MainRepositoryInterface {

    private val parentJob = Job()
    val coroutinesContext: CoroutineContext get() = parentJob + Dispatchers.Default
    val scope = CoroutineScope(coroutinesContext)

    val currentPlaceWeather:
            MutableLiveData<Outcome<WeatherResponse>> = MutableLiveData()

    override suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): Result<T> {
        try {
            val response = call.invoke()
            if (response.isSuccessful)
                return Result.Success(response.body()!!)
            Log.d("SafeError", response.toString())
            return Result.Error(response.message())
        } catch (e: Exception) {
            Log.d("SafeException", e.message ?: "Unknow Exception")
            val exceptionMsg = when (e) {
                is SocketTimeoutException -> ("Connection problem")
                is UnknownHostException -> ("Check internet connection and try again")
                else -> errorMessage
            }
            return Result.Failure(Exception(exceptionMsg))
        }
    }


    override fun getWeatherByPosition(lat: Double, lon: Double) {
        scope.launch {
            val request =
                safeApiCall({ apiService.WeatherByPosition(lat, lon).await() }, "Unable to load weather statistics")
//            currentCityWeather.postValue(response)
            when (request) {
                is Result.Success -> {
                    if (request.data.cod == 200) {
                        try {
                            withContext(Dispatchers.Main) {
                                currentPlaceWeather.success(request.data)
                            }
                        } catch (e: Throwable) {
                            Log.e("weather", "byPOS", e)
                        }
                    } else {

                    }
                }
                is Result.Error -> {

                }
                is Result.Failure -> {

                }
            }
        }
    }

    override fun getWeatherById(id: Int) {
        scope.launch {
            val request =
                safeApiCall({ apiService.WeatherById(id).await() }, "Unable to load weather statistics")
            when (request) {
                is Result.Success -> {
                    if (request.data.cod == 200) {
                        try {
                        withContext(Dispatchers.Main) {
                            currentPlaceWeather.success(request.data)
                        }
                        } catch (e: Throwable) {
                            Log.e("weather", "byID", e)
                        }
                    } else {

                    }
                }
                is Result.Error -> {

                }
                is Result.Failure -> {

                }
            }
        }
    }

    override fun getWeatherByCityName(name: String) {
        scope.launch {
            val request =
                safeApiCall({ apiService.WeatherByName(name).await() }, "Unable to load weather statistics")
            when (request) {
                is Result.Success -> {
                    if (request.data.cod == 200) {
                        try {
                            withContext(Dispatchers.Main) {
                                currentPlaceWeather.success(request.data)
                            }
                        } catch (e: Throwable) {
                            Log.e("weather", "byName", e)
                        }
                    } else {

                    }
                }
                is Result.Error -> {

                }
                is Result.Failure -> {

                }
            }
        }
    }

}