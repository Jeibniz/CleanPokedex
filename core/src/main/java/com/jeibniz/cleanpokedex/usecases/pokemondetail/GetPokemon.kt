package com.jeibniz.cleanpokedex.usecases.pokemondetail

import com.jeibniz.cleanpokedex.data.pokemon.PokemonRepository

class GetPokemon(
    private val repository: PokemonRepository) {
    suspend operator fun invoke(index: Int) = repository.observePokemon(index)
}