package com.jeibniz.cleanpokedex.usecases.pokemonlist

import com.jeibniz.cleanpokedex.data.pokemon.PokemonRepository

class GetGenOnePokemons(
    private val repository: PokemonRepository) {

    private val GenOneLowerLimit = 1
    private val GenOneUpperLimit = 151

    operator fun invoke() = repository.observePokemons(GenOneLowerLimit, GenOneUpperLimit)
}