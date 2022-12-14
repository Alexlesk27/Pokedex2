package com.example.pokedex.features.home.useCase

import com.example.pokedex.ApiRest.repository.PokemonRepository
import com.example.pokedex.support.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface GetListPokemonUseCaseInterface {
    suspend fun execute(page: Int): Flow<ResponseState>
}

class GetListPokemonUseCase(

    private val pokemonRepository: PokemonRepository
) : GetListPokemonUseCaseInterface {
    override suspend fun execute(page: Int): Flow<ResponseState> {
        return flow {
            try {
                val response = pokemonRepository.getListPokemon(page)
                emit(ResponseState.Success(response))
            } catch (error: Exception) {
                emit(ResponseState.Error(error))
            }
        }.flowOn(Dispatchers.IO)
    }
}