package com.jeibniz.cleanpokedex.data.pokemon

import androidx.lifecycle.LiveData
import com.jeibniz.cleanpokedex.data.Resource
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonLocalDataSource {

    fun observeRange(from: Int, to: Int) : Flow<Resource<List<Pokemon>>>

    fun observeSingle(index: Int) : Flow<Resource<Pokemon>>

    suspend fun saveRange(data: List<Pokemon>)

    suspend fun saveSingle(data: Pokemon)

}