package com.example.pokedex.ApiRest

import com.example.pokedex.model.PokemonResponse
import com.example.pokedex.model.PokemonResult
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    @GET("pokemon?limit=150&offset=0.")
    suspend fun getPokemon(
    ):PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getDetails(
        @Path("name") name :String
    ):PokemonResult

}