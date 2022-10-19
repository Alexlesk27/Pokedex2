package com.example.pokedex.ApiRest.repository

import com.example.pokedex.ApiRest.PokemonApi
import com.example.pokedex.model.PokemonResponse
import com.example.pokedex.model.PokemonResult

class PokemonRepository(
    private val pokemonApi: PokemonApi
) {

    suspend fun getListPokemon(): PokemonResponse {
        return pokemonApi.getPokemon()
    }

    suspend fun getDetaisPokemon(name: String): PokemonResult{
        return pokemonApi.getDetails(name)
    }
}