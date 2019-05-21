package br.hadara.weatherapp.util

import java.lang.Exception

sealed class Result<out T: Any> {
    data class Success<out T: Any>(val data: T) : Result<T>()
    data class Failure(val exception: Exception) : Result<Nothing>()
    data class Error(val message: String,val cause: Exception? = null) : Result<Nothing>()
}