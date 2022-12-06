package com.example.pokedex.ApiRest.repository

import com.example.pokedex.ApiRest.PokemonApi
import com.example.pokedex.model.PokemonResponse
import com.example.pokedex.model.PokemonResult

class PokemonRepository(
    private val pokemonApi: PokemonApi
) {

    suspend fun getListPokemon(page: Int): PokemonResponse {
        return pokemonApi.getPokemon(page)
    }

    suspend fun getListPokemonSearch(): PokemonResponse {
        return pokemonApi.getListPokemon()
    }

    suspend fun getDetailsPokemon(name: String): PokemonResult {
        return pokemonApi.getDetails(name)
    }
}