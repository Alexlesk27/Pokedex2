package com.example.pokedex.ApiRest.repository

import com.example.pokedex.ApiRest.PokemonApi
import com.example.pokedex.model.PokemonResponse
import com.example.pokedex.model.PokemonResult
import retrofit2.http.Query

class PokemonRepository(
    private val pokemonApi: PokemonApi
) {

    suspend fun getListPokemon(page: Int): PokemonResponse {
        return pokemonApi.getPokemon(page)
    }

    suspend fun getDetaisPokemon(name: String): PokemonResult {
        return pokemonApi.getDetails(name)
    }
}