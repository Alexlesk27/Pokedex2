package com.example.pokedex.features.search.useCase

import com.example.pokedex.ApiRest.repository.PokemonRepository
import com.example.pokedex.features.home.useCase.GetListPokemonUseCase
import com.example.pokedex.support.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


interface GetListPokemonSearchInterface{
    suspend fun execute(): Flow<ResponseState>
}

class GetListPokemonSearchUseCase(
    private val  pokemonRepository: PokemonRepository
): GetListPokemonSearchInterface {
    override suspend fun execute(): Flow<ResponseState> {
       return flow {
           try {
               val response = pokemonRepository.getListPokemonSearch()
               emit(ResponseState.Success(response))
           }catch (error: Exception){
               emit(ResponseState.Error(error))
           }
       }.flowOn(Dispatchers.IO)
    }
}