package com.example.pokedex.model

import java.io.Serializable

data class PokemonResult(
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>
) : Serializable