package com.example.pokedex.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.features.details.useCase.GetDetailPokemonUseCaseInterface
import com.example.pokedex.model.*
import com.example.pokedex.support.ResponseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PokemonDetailsViewModel(
    private val pokemonUseCase: GetDetailPokemonUseCaseInterface
) : ViewModel() {
    private var _pokemonDetail = MutableStateFlow<ListState<PokemonResult>>(ListState.New)
    val pokemonDetail: StateFlow<ListState<PokemonResult>> = _pokemonDetail.asStateFlow()

    fun getPokemon(name: String) {
        viewModelScope.launch {
            pokemonUseCase.execute(name).onStart {
                _pokemonDetail.value = ListState.Loading
            }.collect {
                when (it) {
                    is ResponseState.Success<*> -> {
                        val pokemonResponse = it.value as PokemonResult
                        _pokemonDetail.value = ListState.Success(pokemonResponse)
                    }
                    is ResponseState.Error -> {
                        _pokemonDetail.value = ListState.Error()
                    }
                }
            }
        }
    }
}
