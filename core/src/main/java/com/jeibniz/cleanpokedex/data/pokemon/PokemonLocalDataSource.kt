package com.jeibniz.cleanpokedex.data.pokemon

import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonLocalDataSource {

    fun getRange(from: Int, to: Int): Flow<List<Pokemon>>

    fun getSingle(index: Int): Flow<Pokemon>

    suspend fun saveList(data: List<Pokemon>)

    suspend fun saveSingle(data: Pokemon)
}
