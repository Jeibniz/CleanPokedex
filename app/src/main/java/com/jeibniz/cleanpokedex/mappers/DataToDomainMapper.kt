package com.jeibniz.cleanpokedex.mappers

import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import com.jeibniz.cleanpokedex.framework.data.local.pokemon.PokemonEntity
import com.jeibniz.cleanpokedex.framework.data.pokemon.remote.model.GeneralPokemonResponse
import com.jeibniz.cleanpokedex.utils.TextUtils

fun GeneralPokemonResponse.toPokemon(): Pokemon {
    val types = types.map { TextUtils.firstLetterToUpperCase(it.type.name) }
    val upperCaseFirstLetterName = TextUtils.firstLetterToUpperCase(name)
    return Pokemon(upperCaseFirstLetterName, number, "", sprites.defaultUrl, types, height, weight)
}

fun PokemonEntity.toPokemon(): Pokemon {
    val typesList = types.split(' ')
    return Pokemon(name, number, description, imageUrl, typesList, height, weight)
}

fun Pokemon.toPokemonEntity(): PokemonEntity {
    val typesString = types.reduce { acc, s -> "$acc $s" }
    val now = System.currentTimeMillis()
    return PokemonEntity(number, name, description, imageUrl, typesString, height, weight, now)
}
