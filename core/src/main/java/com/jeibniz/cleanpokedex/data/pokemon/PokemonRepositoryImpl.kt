package com.jeibniz.cleanpokedex.data.pokemon

import androidx.lifecycle.LiveData
import com.jeibniz.cleanpokedex.data.Resource
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon

class PokemonRepositoryImpl(
    private val localDataSource: PokemonLocalDataSource,
    private val remoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {

    override fun observePokemon(index: Int): LiveData<Resource<Pokemon>> {
        return localDataSource.observeSingle(index)
    }

    override fun observePokemons(from: Int, to: Int): LiveData<Resource<List<Pokemon>>> {
        return localDataSource.observeRange(from, to)
    }


}