package com.example.pokedex.ApiRest

import com.example.pokedex.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemon(
    ):PokemonResponse

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}