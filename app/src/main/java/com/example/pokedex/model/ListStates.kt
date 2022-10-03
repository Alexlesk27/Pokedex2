package com.example.pokedex.model

sealed class ListState<out T>{
    data class Success<out T>(val value: T) : ListState<T>()
    data class Error(val errorId: Int = -1) : ListState<Nothing>()
    object Empty : ListState<Nothing>()
    object Loading : ListState<Nothing>()
    object New : ListState<Nothing>()
}