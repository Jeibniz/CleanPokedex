package com.jeibniz.cleanpokedex.usecases.pokemonlist

import com.jeibniz.cleanpokedex.data.pokemon.PokemonRepository
import javax.inject.Inject

class GetGenOnePokemons @Inject constructor(
    private val repository: PokemonRepository
) {
    private val genOneLowerLimit = 1
    private val genOneUpperLimit = 151

    suspend fun getPokemons() = repository.getPokemons(genOneLowerLimit, genOneUpperLimit)
}
