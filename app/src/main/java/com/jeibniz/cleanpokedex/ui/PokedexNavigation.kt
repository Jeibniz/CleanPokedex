package com.jeibniz.cleanpokedex.ui

sealed class PokedexNavigation(val route: String) {
    object PokedexList : PokedexNavigation("pokedexList")
    object PokemonDetails : PokedexNavigation("pokemonDetails")
}

const val POKEMON_NUMBER_KEY = "pokedexId"
