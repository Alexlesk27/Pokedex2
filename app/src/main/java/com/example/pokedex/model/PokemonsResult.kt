package com.example.pokedex.model

import android.os.Parcelable
import java.io.Serializable

data class PokemonsResult(
    val id: Int,
    val is_default: Boolean,
    val name: String,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
):Serializable