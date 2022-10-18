package com.example.pokedex.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Home(
    @SerializedName("front_default") var frontDefault: String,
    @SerializedName("front_female") var frontFemale: String,
    @SerializedName("front_shiny") var frontShiny: String,
    @SerializedName("front_shiny_female") var frontShinyFemale: String
):Serializable