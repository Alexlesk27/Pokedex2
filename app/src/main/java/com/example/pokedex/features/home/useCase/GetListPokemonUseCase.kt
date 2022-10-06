package com.example.pokedex.features.home.useCase

import com.example.pokedex.ApiRest.repository.PokemonRepository
import com.example.pokedex.model.PokemonResponse
import com.example.pokedex.model.ResponseState

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface GetListPokemonUseCaseInterface {
    suspend fun execute(): Flow<ResponseState<PokemonResponse>>
}

class GetListPokemonUseCase(
    private val pokemonRepository: PokemonRepository
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
}