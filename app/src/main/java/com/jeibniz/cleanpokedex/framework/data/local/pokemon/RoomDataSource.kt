package com.jeibniz.cleanpokedex.framework.data.local.pokemon

import com.jeibniz.cleanpokedex.data.pokemon.PokemonLocalDataSource
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import com.jeibniz.cleanpokedex.mappers.toPokemon
import com.jeibniz.cleanpokedex.mappers.toPokemonEntity
import com.jeibniz.cleanpokedex.data.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomDataSource(
    private val pokemonDao: PokemonDao
) : PokemonLocalDataSource {

    override fun observeRange(from: Int, to: Int): Flow<Result<List<Pokemon>>> {
        return pokemonDao.getRange(from, to).map { Result.Success(it.map { it.toPokemon() }) }
    }

    override fun observeSingle(index: Int): Flow<Result<Pokemon>> {
        return pokemonDao.getByNumber(index).map { Result.Success(it.first().toPokemon())}
    }

    override suspend fun saveRange(data: List<Pokemon>) {
        pokemonDao.insertList(data.map { it.toPokemonEntity() })
    }

    override suspend fun saveSingle(data: Pokemon) {
        pokemonDao.insert(data.toPokemonEntity())
    }
}