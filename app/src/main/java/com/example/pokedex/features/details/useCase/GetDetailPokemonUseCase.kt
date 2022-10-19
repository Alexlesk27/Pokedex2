package com.example.pokedex.features.details.useCase

import com.example.pokedex.ApiRest.repository.PokemonRepository
import com.example.pokedex.support.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetDetailPokemonUseCaseInterface{
    suspend fun execute(name: String): Flow<ResponseState>
}

class GetDetailPokemonUseCase(
    private val pokemonRepository: PokemonRepository
):GetDetailPokemonUseCaseInterface {
    override suspend fun execute(name: String): Flow<ResponseState> {
        return flow {
            try {
                val response = pokemonRepository.getDetaisPokemon(name)
                emit(ResponseState.Success(response))
            }catch (error: Exception){
                emit(ResponseState.Error(error))
            }
        }
    }
}