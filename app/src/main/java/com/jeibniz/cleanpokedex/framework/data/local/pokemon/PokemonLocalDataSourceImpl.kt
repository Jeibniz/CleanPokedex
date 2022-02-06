package com.jeibniz.cleanpokedex.framework.data.local.pokemon

import com.jeibniz.cleanpokedex.data.pokemon.PokemonLocalDataSource
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import com.jeibniz.cleanpokedex.mappers.toPokemon
import com.jeibniz.cleanpokedex.mappers.toPokemonEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PokemonLocalDataSourceImpl @Inject constructor(
    private val pokemonDao: PokemonDao
) : PokemonLocalDataSource {

    override fun getRange(from: Int, to: Int): Flow<List<Pokemon>> {
        return pokemonDao.getRange(from, to).map { list ->
            return@map list.map { it.toPokemon() }
        }
    }

    override fun getSingle(index: Int): Flow<Pokemon> {
        return pokemonDao.getByNumber(index).map { it.first().toPokemon() }
    }

    override suspend fun saveList(data: List<Pokemon>) {
        pokemonDao.insertList(data.map { it.toPokemonEntity() })
    }

    override suspend fun saveSingle(data: Pokemon) {
        pokemonDao.insert(data.toPokemonEntity())
    }
}
