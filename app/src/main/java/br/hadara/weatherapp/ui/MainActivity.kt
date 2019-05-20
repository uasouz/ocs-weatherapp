package br.hadara.weatherapp.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer
import br.hadara.weatherapp.R
import br.hadara.weatherapp.domain.MainViewModel
import dagger.android.AndroidInjection.inject

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inject(this)
        initializeWeatherDataObserver()
        loadWeatherById(3663517)
    }

    fun initializeWeatherDataObserver() {
        viewModel.currentCityWeather.observe(this, Observer { weather ->
            tvCityName.text = weather.name
            tvTemperature.text = weather.weather.first().main
        })
    }

    fun loadWeatherById(id: Int) {
        viewModel.GetWeatherById(id)
    }

    fun loadWeatherByPosition(lat: Float, lon: Float) {
        viewModel.GetWeatherByPosition(lat, lon)
    }

}
