package com.example.pokedex.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.features.search.useCase.GetListPokemonSearchInterface
import com.example.pokedex.model.ListState
import com.example.pokedex.model.PokemonResponse
import com.example.pokedex.support.ResponseState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.http.Query

class SearchPokemonViewModel(
    private val pokemonUseCase: GetListPokemonSearchInterface
): ViewModel() {

    private var _pokemonSearch = MutableStateFlow<ListState<PokemonResponse>>(ListState.New)
    val pokemonSearch: StateFlow<ListState<PokemonResponse>> = _pokemonSearch.asStateFlow()
init {
    getPokemon()
}

  private  fun getPokemon(){
        viewModelScope.launch {
            pokemonUseCase.execute().onStart {
                _pokemonSearch.value = ListState.Loading
            }.collect{
                when(it){
                    is ResponseState.Success<*> ->{
                        val pokemonResponse = it.value as PokemonResponse
                        _pokemonSearch.value = ListState.Success(pokemonResponse)
                    }
                    is  ResponseState.Error ->{
                        _pokemonSearch.value =ListState.Error(it.error.message)
                    }
                }
            }
        }
    }


}