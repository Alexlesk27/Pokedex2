package com.example.pokedex.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pokemon(
    @SerializedName("name") val name: String,
): Serializable
