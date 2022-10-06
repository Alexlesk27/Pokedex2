package com.example.pokedex.ApiRest

import com.example.pokedex.model.PokemonDetails
import com.example.pokedex.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemon(
    ):PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getDetailsPokemon(
        @Path("name") name: String
    ):PokemonDetails

    companion object {
         val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}