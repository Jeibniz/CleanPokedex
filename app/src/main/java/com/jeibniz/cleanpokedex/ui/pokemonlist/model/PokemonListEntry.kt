package com.jeibniz.cleanpokedex.ui.pokemonlist.model

data class PokemonListEntry(
    val name: String,
    val number: Int,
    val types: List<String>,
    val imageUrl: String
)
