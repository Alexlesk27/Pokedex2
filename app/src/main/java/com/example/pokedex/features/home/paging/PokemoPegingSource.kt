package com.example.pokedex.features.home.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokedex.features.home.useCase.GetListPokemonUseCase
import com.example.pokedex.features.home.useCase.GetListPokemonUseCaseInterface
import com.example.pokedex.model.ListState
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonResponse
import com.example.pokedex.support.NETWORK_PAGE_SIZE
import com.example.pokedex.support.ResponseState
import com.example.pokedex.support.STARTING_PAGE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PokemoPegingSource(
    val pokemonRepository: GetListPokemonUseCaseInterface
) : PagingSource<Int, Pokemon>() {

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(20)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val page = params.key ?: 0
            Log.i("page", "$page")
            var response: PokemonResponse? = null

            pokemonRepository.execute(page = page).collect {
                Log.i("params", "${params.loadSize}")
                when (it) {
                    is ResponseState.Success<*> -> {
                        response = it.value as PokemonResponse
                    }
                    else -> Unit
                }
            }

            val nextKey =
                if (response?.pokemon.isNullOrEmpty()) null else page.plus(NETWORK_PAGE_SIZE)
            LoadResult.Page(
                data = response?.pokemon ?: emptyList(),
                prevKey = if (page == 0) null else page.minus(20),
                nextKey = nextKey,
            )
        } catch (error: Exception) {
            LoadResult.Error(error)
        }
    }
}