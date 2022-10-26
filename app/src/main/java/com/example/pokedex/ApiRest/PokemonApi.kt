package com.example.pokedex.ApiRest

import com.example.pokedex.model.PokemonResponse
import com.example.pokedex.model.PokemonResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon/?limit=20")
    suspend fun getPokemon(
        @Query("offset") page: Int

    ): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getDetails(
        @Path("name") name: String
    ): PokemonResult

}