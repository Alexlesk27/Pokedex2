package com.example.pokedex.ApiRest.repository

import com.example.pokedex.ApiRest.PokemonApi
import com.example.pokedex.model.PokemonResponse

class PokemonRepository(
    private val pokemonApi: PokemonApi
) {

    suspend fun getListPokemon(): PokemonResponse {
        return pokemonApi.getPokemon()
    }
}