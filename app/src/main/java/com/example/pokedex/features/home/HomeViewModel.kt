package com.example.pokedex.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.features.home.useCase.GetListPokemonUseCaseInterface
import com.example.pokedex.model.ListState
import com.example.pokedex.model.Pokemon
import com.example.pokedex.support.ResponseState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    val listPokemon: GetListPokemonUseCaseInterface
) : ViewModel() {

    private var _pokemon = MutableStateFlow<ListState<List<Pokemon>>>(ListState.New)
    val pokemon: StateFlow<ListState<List<Pokemon>>> = _pokemon.asStateFlow()

init {
    getPokemon()
}

    private fun getPokemon() {
       viewModelScope.launch {
           listPokemon.execute().onStart {
               _pokemon.value = ListState.Loading
           }.collect{
               when(it){
                   is ResponseState.Success->{
                       _pokemon.value = ListState.Success(it.value.pokemon)
                   }
                   is ResponseState.Error->{
                       _pokemon.value = ListState.Error()
                   }
               }
           }
       }
    }
}