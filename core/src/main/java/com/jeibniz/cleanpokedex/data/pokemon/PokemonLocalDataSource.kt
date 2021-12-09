package com.jeibniz.cleanpokedex.data.pokemon

import com.jeibniz.cleanpokedex.data.Result
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonLocalDataSource {

    fun observeRange(from: Int, to: Int) : Flow<Result<List<Pokemon>>>

    fun observeSingle(index: Int) : Flow<Result<Pokemon>>

    suspend fun saveRange(data: List<Pokemon>)

    suspend fun saveSingle(data: Pokemon)

}