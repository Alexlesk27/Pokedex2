package com.example.pokedex.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.model.ListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewmodel() : ViewModel() {

    private var _home = MutableStateFlow<ListState<List<String>>>(ListState.New)
    val home: StateFlow<ListState<List<String>>> = _home

    init {
       getPokemon()
    }

  private fun getPokemon() {
        viewModelScope.launch {
            getListFlow().collect {
                when (it) {
                    is ResponseState.Success -> {
                        _home.value = ListState.Success(it.value)
                    }

                    else -> {

                    }
                }
            }
        }
    }

 private fun getListFlow(): Flow<HomeViewmodel.ResponseState<List<String>>> {
        return flow {
            try {
                val response = arrayListOf(
                    "pikachu",
                    "Bulbasaur",
                    "Ivysaur",
                    "Charmander",
                    "Croconaw"
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
