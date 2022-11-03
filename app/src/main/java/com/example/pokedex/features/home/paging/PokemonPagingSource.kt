package com.example.pokedex.features.home.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokedex.features.home.useCase.GetListPokemonUseCaseInterface
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonResponse
import com.example.pokedex.support.NETWORK_PAGE_SIZE
import com.example.pokedex.support.ResponseState
import com.example.pokedex.support.STARTING_PAGE

class PokemonPagingSource(
    val pokemonRepository: GetListPokemonUseCaseInterface
) : PagingSource<Int, Pokemon>() {

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(20)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(20)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val page = params.key ?: STARTING_PAGE
            var response: PokemonResponse? = null

            pokemonRepository.execute(page = page).collect {
                when (it) {
                    is ResponseState.Success<*> -> {
                        response = it.value as PokemonResponse
                    }
                    is ResponseState.Error -> {
                        throw it.error
                    }
                }
            }

            val nextKey = if (response?.pokemon.isNullOrEmpty()) null
            else page.plus(NETWORK_PAGE_SIZE)
            LoadResult.Page(
                data = response?.pokemon?: emptyList(),
                prevKey = if (page == 0) null else page.minus(20),
                nextKey = nextKey,
            )
        } catch (error: Exception) {
            LoadResult.Error(error)
        }
    }
}