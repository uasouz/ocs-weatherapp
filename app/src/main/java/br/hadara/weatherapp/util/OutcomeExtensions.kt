package br.hadara.weatherapp.util

import androidx.lifecycle.MutableLiveData

/**
 * Extension function to push a failed event with an exception to the observing outcome
 * */
fun <T> MutableLiveData<Outcome<T>>.failed(e: Throwable) {
    with(this){
        loading(false)
        value = Outcome.failure(e)
    }
}

/**
 * Extension function to push a error event with an message to the observing outcome
 * */
fun <T> MutableLiveData<Outcome<T>>.error(s: String) {
    with(this){
        loading(false)
        value = Outcome.error(s)
    }
}

/**
 * Extension function to push  a success event with data to the observing outcome
 * */
fun <T> MutableLiveData<Outcome<T>>.success(t: T) {
    with(this){
        loading(false)
        value = Outcome.success(t)
    }
}

/**
 * Extension function to push the loading status to the observing outcome
 * */
fun <T> MutableLiveData<Outcome<T>>.loading(isLoading: Boolean) {
    value = Outcome.loading(isLoading)
}
