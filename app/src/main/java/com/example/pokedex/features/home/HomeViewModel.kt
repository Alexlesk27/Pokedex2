package com.example.pokedex.features.home

import androidx.lifecycle.ViewModel
import com.example.pokedex.features.home.useCase.GetListPokemonUseCaseInterface
import com.example.pokedex.model.ListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(
    val listPokemon: GetListPokemonUseCaseInterface
) : ViewModel() {

    private var _pokemon = MutableStateFlow<ListState<List<String>>>(ListState.New)
    val pokemon: StateFlow<ListState<List<String>>> = _pokemon

    init {
        getPokemon()
    }

    private fun getPokemon() {
        _pokemon.value = ListState.Success(listPokemon.execute())
    }

}