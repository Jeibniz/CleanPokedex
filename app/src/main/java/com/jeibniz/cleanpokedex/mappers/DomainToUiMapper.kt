package com.jeibniz.cleanpokedex.ui.pokemonlist

import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import com.jeibniz.cleanpokedex.ui.pokemonlist.model.PokemonListEntry

fun Pokemon.toPokemonListEntry() =
    PokemonListEntry(
        imageUrl = imageUrl,
        name = name,
        number = number,
        types = types
    )
