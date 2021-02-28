package com.jeibniz.cleanpokedex.framework.data.remote.pokemon.model

import com.google.gson.annotations.SerializedName

data class PokemonDescription(
    @SerializedName("flavor_text") val description: String,
    val language: DescriptionLanguage
)
