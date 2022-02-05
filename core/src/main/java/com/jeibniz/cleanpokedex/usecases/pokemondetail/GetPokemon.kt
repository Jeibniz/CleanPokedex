package com.jeibniz.cleanpokedex.usecases.pokemondetail

import com.jeibniz.cleanpokedex.data.pokemon.PokemonRepository

class GetPokemon(
    private val repository: PokemonRepository
) {
    fun observePokemon() = repository.observePokemon()

    suspend fun requestPokemon(index: Int) = repository.requestPokemon(index)
}
