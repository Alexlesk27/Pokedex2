package com.example.pokedex.model

import com.google.gson.annotations.SerializedName

data class Pokemon (
    val number : Int,
    val name : String,
    val image: String,
    val results: List<PokemonsResult>

){
    val formattedNumber = number.toString().padStart(3, '0')
    val imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/$formattedNumber.png"
}
