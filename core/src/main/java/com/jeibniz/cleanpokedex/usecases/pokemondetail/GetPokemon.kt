package com.jeibniz.cleanpokedex.usecases.pokemondetail

import com.jeibniz.cleanpokedex.data.pokemon.PokemonRepository
import javax.inject.Inject

class GetPokemon @Inject constructor(
    private val repository: PokemonRepository
) {
    fun observePokemon() = repository.observePokemon()

    suspend fun requestPokemon(index: Int) = repository.requestPokemon(index)
}
