package com.example.pokedex.features.home.useCase

import com.example.pokedex.ApiRest.repository.GetPokemonRepository
import com.example.pokedex.model.PokemonResponse

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface GetListPokemonUseCaseInterface {
    suspend fun execute(): Flow<GetListPokemonUseCase.ResponseState<PokemonResponse>>
}

class GetListPokemonUseCase(
    private val pokemonRepository: GetPokemonRepository
):GetListPokemonUseCaseInterface  {
    override suspend fun execute():Flow<ResponseState<PokemonResponse>> {
        return flow {
            try {
                val response = pokemonRepository.getListPokemon()
                emit(ResponseState.Success(response))
            } catch (error : Exception){
                emit(ResponseState.Error(error))
            }
        }.flowOn(Dispatchers.IO)
    }

    sealed class ResponseState<out T> {
        data class Success<out T>(val value: T) : ResponseState<T>()
        data class Error(val error: Throwable) : ResponseState<Nothing>()
    }
}