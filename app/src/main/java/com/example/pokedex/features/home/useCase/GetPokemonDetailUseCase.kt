package com.example.pokedex.features.home.useCase

import com.example.pokedex.ApiRest.repository.PokemonRepository
import com.example.pokedex.model.PokemonDetails
import com.example.pokedex.model.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
//
//interface GetPokemonDetailUseCaseInterface {
//    suspend fun execute(name: String): Flow<ResponseState<PokemonDetails>>
//}
//
//class GetPokemonDetailUseCase(
//    private val pokemonRepository: PokemonRepository
//):GetPokemonDetailUseCaseInterface {
//
//    override suspend fun execute(name: String): Flow<ResponseState<PokemonDetails>> {
//        return flow {
//            try {
//                val response = pokemonRepository.getDetailsPokemon(name)
//                emit(ResponseState.Success(response))
//            }catch (error: Exception){
//                emit(ResponseState.Error(error))
//            }
//        }
//    }
//}
