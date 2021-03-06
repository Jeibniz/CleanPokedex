package com.jeibniz.cleanpokedex.framework.data.local.pokemon

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jeibniz.cleanpokedex.data.Resource
import com.jeibniz.cleanpokedex.data.pokemon.PokemonLocalDataSource
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import com.jeibniz.cleanpokedex.mappers.toPokemon
import com.jeibniz.cleanpokedex.mappers.toPokemonEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomDataSource(
    private val pokemonDao: PokemonDao
) : PokemonLocalDataSource {

    override fun observeRange(from: Int, to: Int): Flow<Resource<List<Pokemon>>> {
        return pokemonDao.getRange(from, to).map { Resource.success(it.map { it.toPokemon() }) }
    }

    override fun observeSingle(index: Int): Flow<Resource<Pokemon>> {
        return pokemonDao.getByNumber(index).map { Resource.success(it.first().toPokemon())}
    }

    override suspend fun saveRange(data: List<Pokemon>) {
        pokemonDao.insertList(data.map { it.toPokemonEntity() })
    }

    override suspend fun saveSingle(data: Pokemon) {
        pokemonDao.insert(data.toPokemonEntity())
    }
}