package com.jeibniz.cleanpokedex.data.pokemon

import com.jeibniz.cleanpokedex.data.Result
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon

interface PokemonRemoteDataSource {
    suspend fun getRange(from: Int, to: Int): Result<List<Pokemon>>

    suspend fun getSingle(index: Int): Result<Pokemon>
}
