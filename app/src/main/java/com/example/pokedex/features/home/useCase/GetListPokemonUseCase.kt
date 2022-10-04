package com.example.pokedex.features.home.useCase

interface GetListPokemonUseCaseInterface {
    fun execute(): List<String>
}

class GetListPokemonUseCase() : GetListPokemonUseCaseInterface {
    override fun execute(): List<String> {
        return listOf(
            "pikachu",
            "Bulbasaur",
            "Ivysaur",
            "Charmander",
            "Croconaw",
        )

    }
}