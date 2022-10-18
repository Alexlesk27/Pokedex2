package com.example.pokedex.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TypeX(
    @SerializedName("name") var name: String,
    @SerializedName("url") var url: String
):Serializable