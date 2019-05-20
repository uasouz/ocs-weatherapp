package br.hadara.weatherapp.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.hadara.weatherapp.data.WeatherService
import br.hadara.weatherapp.domain.model.WeatherResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class MainViewModel @Inject constructor(val apiService: WeatherService) : ViewModel() {

    val currentCityWeather: MutableLiveData<WeatherResponse> = MutableLiveData()

    fun GetWeatherByPosition(lat: Float, lon: Float){
        GlobalScope.async {
            val response = apiService.WeatherByPosition(lat, lon).await()
            currentCityWeather.postValue(response)
        }
    }

    fun GetWeatherById(id: Int){
        GlobalScope.async {
            val response = apiService.WeatherById(id).await()
            currentCityWeather.postValue(response)
        }
    }

}