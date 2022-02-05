package com.jeibniz.cleanpokedex.usecases.pokemonlist

import com.jeibniz.cleanpokedex.data.pokemon.PokemonRepository

class GetGenOnePokemons(
    private val repository: PokemonRepository
) {

    private val genOneLowerLimit = 1
    private val genOneUpperLimit = 151

    fun observePokemons() = repository.observePokemons()
    suspend fun requestPokemons() = repository.requestPokemons(genOneLowerLimit, genOneUpperLimit)
}
