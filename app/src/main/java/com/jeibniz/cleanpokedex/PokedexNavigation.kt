package com.jeibniz.cleanpokedex

sealed class PokedexNavigation(val route: String) {
    object PokedexList : PokedexNavigation("pokedexList")
    object PokemonDetails : PokedexNavigation("pokemonDetails")
}

const val POKEMON_NAME_KEY = "pokedexName"