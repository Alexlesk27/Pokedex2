package com.example.pokedex.features.home.useCase

import com.example.pokedex.repository.GetListPokemonRepository

interface GetListPokemonUseCaseInterface {
    fun execute(): List<String>
}

class GetListPokemonUseCase(
    private val pokemonRepository: GetListPokemonRepository
):GetListPokemonUseCaseInterface  {
    override fun execute(): List<String> {
        return  pokemonRepository.getListPokemon()
    }

}