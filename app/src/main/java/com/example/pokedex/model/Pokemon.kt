package com.example.pokedex.model

data class Pokemon (
    val number : Int,
    val name : String,
    val image: String,
    val results: List<PokemonResult>

)
