package com.example.pokedex.features.home.useCase

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
