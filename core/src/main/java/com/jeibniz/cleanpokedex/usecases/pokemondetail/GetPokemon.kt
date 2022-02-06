package com.jeibniz.cleanpokedex.usecases.pokemondetail

import com.jeibniz.cleanpokedex.data.pokemon.PokemonRepository
import javax.inject.Inject

class GetPokemon @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend fun getPokemon(number: Int) = repository.getPokemon(number)
}
