package com.jeibniz.cleanpokedex.usecases.pokemonlist

import com.jeibniz.cleanpokedex.data.pokemon.PokemonRepository

class GetPokemons(
    private val repository: PokemonRepository) {
    suspend operator fun invoke(from: Int, to: Int) = repository.getPokemons(from, to)
}