package com.example.pokedex.features

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.model.PokemonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


class HomeViewmodel() : ViewModel() {

    private val _home = MutableLiveData<List<String>>()
    val home: LiveData<List<String>> get() = _home


    init {
        getList()
    }

    fun getList() {
        _home.value = listOf(
            "pikachu",
            "bulbassaur",
            "charmander"
        )
    }

}
