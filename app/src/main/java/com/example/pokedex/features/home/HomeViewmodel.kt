package com.example.pokedex.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.model.ListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewmodel() : ViewModel() {

    private var _pokemon = MutableStateFlow<ListState<List<String>>>(ListState.New)
    val pokemon: StateFlow<ListState<List<String>>> = _pokemon

    init {
       getPokemon()
    }

  private fun getPokemon() {
        viewModelScope.launch {
            getList().collect {
                when (it) {
                    is ResponseState.Success -> {
                        _pokemon.value = ListState.Success(it.value)
                    }

                    else -> {

                    }
                }
            }
        }
    }

 private fun getList(): Flow<ResponseState<List<String>>> {
        return flow {
            try {
                val response = arrayListOf(
                    "pikachu",
                    "Bulbasaur",
                    "Ivysaur",
                    "Charmander",
                    "Croconaw",

                )
                emit(ResponseState.Success(response))
            } catch (erro: Exception) {

            }
        }.flowOn(Dispatchers.IO)
    }

    sealed class ResponseState<out T> {
        data class Success<out T>(val value: T) : ResponseState<T>()
        data class Error(val error: Throwable) : ResponseState<Nothing>()
    }

}
