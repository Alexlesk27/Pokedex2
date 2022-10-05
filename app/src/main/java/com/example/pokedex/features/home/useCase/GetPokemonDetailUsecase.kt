package com.example.pokedex.features.home.useCase

import com.example.pokedex.ApiRest.repository.GetPokemonRepository
import com.example.pokedex.model.PokemonDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetPokemonDetailUsecaseInterface {
    suspend fun execute(name: String): Flow<GetPokemonDetailUsecase.ResponseState<PokemonDetails>>
}

class GetPokemonDetailUsecase(
    private val pokemonRepository: GetPokemonRepository
):GetPokemonDetailUsecaseInterface {

    override suspend fun execute(name: String): Flow<ResponseState<PokemonDetails>> {
        return flow {
            try {
                val response = pokemonRepository.getDetailsPokemon(name)
                emit(ResponseState.Success(response))
            }catch (error: Exception){
                emit(ResponseState.Error(error))
            }
        }
    }

    sealed class ResponseState<out T> {
        data class Success<out T>(val value: T) : ResponseState<T>()
        data class Error(val error: Throwable) : ResponseState<Nothing>()

    }

}
