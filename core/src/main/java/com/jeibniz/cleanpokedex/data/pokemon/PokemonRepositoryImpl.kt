package com.jeibniz.cleanpokedex.data.pokemon

import androidx.lifecycle.LiveData
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon

class PokemonRepositoryImpl(
    private val localDataSource: PokemonDataSource,
    private val remoteDataSource: PokemonDataSource
) : PokemonRepository {

    override suspend fun getPokemon(index: Int): LiveData<Resource<Pokemon>> {
        return remoteDataSource.getSingle(index)
    }

    override suspend fun getPokemons(from: Int, to: Int): LiveData<Resource<List<Pokemon>>> {
        return remoteDataSource.getRange(from, to)
    }


}