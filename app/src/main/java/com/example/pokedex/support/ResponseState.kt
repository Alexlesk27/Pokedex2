package com.example.pokedex.support

sealed class ResponseState {
    data class Success<out T>(val value: T) : ResponseState()
    data class Error(val error: Throwable) : ResponseState()
}