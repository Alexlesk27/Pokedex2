package com.example.pokedex.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokemonResult(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("sprites") var sprites: Sprites,
    @SerializedName("stats") var stats: List<Stat>,
    @SerializedName("types") var types: List<Type>
) : Serializable