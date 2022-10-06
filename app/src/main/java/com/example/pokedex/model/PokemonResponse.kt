package com.example.pokedex.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("results")
    val pokemon: List<Pokemon>
)