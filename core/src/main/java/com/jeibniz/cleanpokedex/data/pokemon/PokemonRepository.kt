package com.jeibniz.cleanpokedex.data.pokemon

import com.jeibniz.cleanpokedex.data.Result
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun observePokemon(): Flow<Result<Pokemon>>

    fun observePokemons(): Flow<Result<List<Pokemon>>>

    suspend fun requestPokemon(index: Int)

    suspend fun requestPokemons(from: Int, to: Int)
}