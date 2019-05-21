package br.hadara.weatherapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.hadara.weatherapp.domain.MainRepository
import br.hadara.weatherapp.domain.model.WeatherResponse
import br.hadara.weatherapp.util.Outcome
import javax.inject.Inject

class MainViewModel @Inject constructor(val repository: MainRepository) : ViewModel() {

    val currentCityWeather: MutableLiveData<WeatherResponse> = repository.currentPlaceWeather


    fun getWeatherByPosition(lat: Double, lon: Double) {
        repository.getWeatherByPosition(lat, lon)
    }


    fun getWeatherById(id: Int) {
        repository.getWeatherById(id)
    }

}