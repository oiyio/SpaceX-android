package com.omeriyioz.spacex

sealed class ViewState<out T : Any> {

    data class Success<out T : Any>(val result: T) : ViewState<T>()
    data class Error(val exception: Exception) : ViewState<Nothing>()
    object Loading : ViewState<Nothing>()

}

