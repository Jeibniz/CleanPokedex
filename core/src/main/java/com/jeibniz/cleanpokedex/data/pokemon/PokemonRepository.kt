package com.jeibniz.cleanpokedex.data.pokemon

import com.jeibniz.cleanpokedex.data.Result
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemons(from: Int, to: Int): Flow<Result<List<Pokemon>>>

    suspend fun getPokemon(number: Int): Flow<Result<Pokemon>>
}
