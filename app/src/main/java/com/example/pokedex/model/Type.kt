package com.example.pokedex.model

import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") var type: TypeX
)