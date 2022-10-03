package com.example.pokedex.features.home

import androidx.lifecycle.ViewModel
import com.example.pokedex.model.ListState
import kotlinx.coroutines.flow.*


class HomeViewModel() : ViewModel() {

    private var _pokemon = MutableStateFlow<ListState<List<String>>>(ListState.New)
    val pokemon: StateFlow<ListState<List<String>>> = _pokemon

    init {
        getPokemon()
    }

    private fun getPokemon() {
        _pokemon.value = ListState.Success(getList())
    }

    private fun getList(): List<String> {
        return listOf(
            "pikachu",
            "Bulbasaur",
            "Ivysaur",
            "Charmander",
            "Croconaw",
        )
    }
}