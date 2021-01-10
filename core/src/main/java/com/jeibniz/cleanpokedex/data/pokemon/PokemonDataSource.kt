package com.jeibniz.cleanpokedex.data.pokemon

import androidx.lifecycle.LiveData
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon

interface PokemonDataSource {

    suspend fun getRange(from: Int, to: Int) : LiveData<Resource<List<Pokemon>>>

    suspend fun getSingle(index: Int) : LiveData<Resource<Pokemon>>

}