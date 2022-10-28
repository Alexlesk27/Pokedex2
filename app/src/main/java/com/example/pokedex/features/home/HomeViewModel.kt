package com.example.pokedex.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pokedex.features.home.paging.PokemoPegingSource
import com.example.pokedex.features.home.useCase.GetListPokemonUseCaseInterface
import com.example.pokedex.model.Pokemon
import com.example.pokedex.support.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.*

class HomeViewModel(
    private val listPokemon: GetListPokemonUseCaseInterface,
) : ViewModel() {

    fun getPokemon(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                initialLoadSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PokemoPegingSource(pokemonRepository = listPokemon)
            }
        ).flow.cachedIn(viewModelScope)
    }
}