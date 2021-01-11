package com.jeibniz.cleanpokedex.data.pokemon

import com.jeibniz.cleanpokedex.data.Resource
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon

interface PokemonRemoteDataSource {

    suspend fun getRange(from: Int, to: Int) : Resource<List<Pokemon>>

    suspend fun getSingle(index: Int) : Resource<Pokemon>

}