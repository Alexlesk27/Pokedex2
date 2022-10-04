package com.example.pokedex.repository

import com.example.pokedex.ApiRest.PokemonApi
import com.example.pokedex.model.PokemonResponse

class GetListPokemonRepository(
    private val pokemonApi: PokemonApi
) {

    suspend fun getListPokemon(): PokemonResponse {
        return pokemonApi.getPokemon()
    }

}