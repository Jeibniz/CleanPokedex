package com.jeibniz.cleanpokedex.framework.data.remote

data class SinglePokemonResponse(
    val name: String,
    val types: List<TypeResponse>,
    val height: Int,
    val weight: Int,
    val sprites: List<SpriteResponse>
)

