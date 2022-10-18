package com.example.pokedex.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Other(
    @SerializedName("home") var home: Home
    ):Serializable