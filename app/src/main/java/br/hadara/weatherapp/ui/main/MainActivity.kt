package br.hadara.weatherapp.ui.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import br.hadara.weatherapp.R
import br.hadara.weatherapp.data.Constants.IMG_URL
import br.hadara.weatherapp.util.Outcome
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection.inject
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModel: MainViewModel

    val ACCESS_FINE_LOCATION_REQUEST_ID = 10021
    val REQUEST_CHECK_SETTINGS = 10022

    private var fusedLocationClient: FusedLocationProviderClient? = null
    private lateinit var locationCallback: LocationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inject(this)
        initializeWeatherDataObserver()
        getLocation()
    }

    private fun initializeWeatherDataObserver() {
        viewModel.currentCityWeather.observe(this, Observer { weatherOucome ->
            when(weatherOucome){
                is Outcome.Success-> {
                    Log.d("Success","test")
                        tvCityName.text = weatherOucome.data.name
                        tvTemperature.text = weatherOucome.data.main.temp.roundToInt().toString()
                        tvDescription.text = weatherOucome.data.weather.first().description
                        Picasso.get().load(IMG_URL + weatherOucome.data.weather.first().icon + ".png")
                            .into(ivWeatherIcon)
                }
                is Outcome.Failure->{

                }
                is Outcome.Error->{

                }
            }
        })
    }

    fun loadWeatherById(id: Int) {
        viewModel.getWeatherById(id)
    }

    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(
//                    this,
//                    Manifest.permission.ACCESS_FINE_LOCATION
//                )
//            ) {
//
//                Log.d("Permission","Rationale Request")
//
//            } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                ACCESS_FINE_LOCATION_REQUEST_ID
            )
        }
//        }
    }

    /**
     * GMS Location API
     */
    fun getLocation() {


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val lastLocationTask = fusedLocationClient?.lastLocation

        lastLocationTask?.addOnSuccessListener { location: Location? ->
            if (location != null) {
                loadWeatherByPosition(location)
            } else {
                Log.d("Null Location", "")
            }
        }
        lastLocationTask?.addOnFailureListener {

        }


        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations) {
                    loadWeatherByPosition(location)
                }
            }
        }

        val locationRequest = LocationRequest().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
            numUpdates = 1
        }

        val builder: LocationSettingsRequest.Builder =
            LocationSettingsRequest.Builder().addLocationRequest(locationRequest)

        val client: SettingsClient = LocationServices.getSettingsClient(this)

        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener { _ ->
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.d("Permission", "Rquesting")
                requestLocationPermission()
            } else {
                fusedLocationClient?.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
            }
        }

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    exception.startResolutionForResult(
                        this@MainActivity,
                        REQUEST_CHECK_SETTINGS
                    )
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            ACCESS_FINE_LOCATION_REQUEST_ID -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation()
                } else {

                }
            }
        }
    }

    fun loadWeatherByPosition(location: Location) {
        Log.d("Loading","Position")
        viewModel.getWeatherByPosition(location.latitude, location.longitude)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == Activity.RESULT_OK) {
                getLocation()
            }
        }
    }

    fun configureSearchView() {

    }

}
