package br.hadara.weatherapp.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.hadara.weatherapp.data.WeatherService
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory @Inject constructor(private val apiService: WeatherService) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}