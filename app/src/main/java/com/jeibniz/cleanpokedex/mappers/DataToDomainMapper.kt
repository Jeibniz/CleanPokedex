package com.jeibniz.cleanpokedex.mappers

import android.util.Log
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import com.jeibniz.cleanpokedex.framework.data.local.pokemon.PokemonEntity
import com.jeibniz.cleanpokedex.framework.data.pokemon.remote.model.GeneralPokemonResponse
import com.jeibniz.cleanpokedex.ui.pokemonlist.model.PokemonListEntry

fun GeneralPokemonResponse.toPokemon () : Pokemon {
    val types = types.map {it.type.name}
    return Pokemon(name, number, "", sprites.defaultUrl, types, height, weight)
}

fun PokemonEntity.toPokemon () : Pokemon {
    val typesList = types.split(' ')
    return Pokemon(name, number, description, imageUrl, typesList, height, weight)
}

fun Pokemon.toPokemonEntity () : PokemonEntity {
    val typesString = types.reduce { acc, s -> String.format("%s %s", acc, s) }
    Log.d("Mapper", "toPokemonEntity: " + this)
    return PokemonEntity(number, name, description, imageUrl, typesString, height, weight)
}
