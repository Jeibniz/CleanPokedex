package com.jeibniz.cleanpokedex.mappers

import android.util.Log
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import com.jeibniz.cleanpokedex.framework.data.pokemon.remote.model.GeneralPokemonResponse
import com.jeibniz.cleanpokedex.ui.pokemonlist.model.PokemonListEntry

fun GeneralPokemonResponse.toPokemon () : Pokemon {
    val types = types.map {it.name}
    return Pokemon(name, number, "", sprites.defaultUrl, types, height, weight)
}
