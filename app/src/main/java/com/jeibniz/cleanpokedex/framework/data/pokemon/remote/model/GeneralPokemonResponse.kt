package com.jeibniz.cleanpokedex.framework.data.pokemon.remote.model

import com.google.gson.annotations.SerializedName

data class GeneralPokemonResponse(
    val name: String,
    @SerializedName("id") val number: Int,
    val types: List<TypeResponse>,
    val height: Int,
    val weight: Int,
    val sprites: SpriteResponse
)

