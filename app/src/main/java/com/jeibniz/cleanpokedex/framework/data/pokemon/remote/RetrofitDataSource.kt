package com.jeibniz.cleanpokedex.framework.data.remote.pokemon

import androidx.lifecycle.LiveData
import com.jeibniz.cleanpokedex.data.pokemon.PokemonRemoteDataSource
import com.jeibniz.cleanpokedex.data.Resource
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon

class RetrofitDataSource: PokemonRemoteDataSource {
    override suspend fun getRange(from: Int, to: Int): Resource<List<Pokemon>> {

        TODO("Not yet implemented")
    }

    override suspend fun getSingle(index: Int): Resource<Pokemon> {
        TODO("Not yet implemented")
    }

}