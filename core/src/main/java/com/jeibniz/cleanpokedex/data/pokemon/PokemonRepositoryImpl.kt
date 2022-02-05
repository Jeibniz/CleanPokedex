package com.jeibniz.cleanpokedex.data.pokemon

import android.util.Log
import com.jeibniz.cleanpokedex.data.Result
import com.jeibniz.cleanpokedex.data.succeeded
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PokemonRepositoryImpl(
    private val localDataSource: PokemonLocalDataSource,
    private val remoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {

    private val TAG = "PokemonRepositoryImpl"

    val pokemonFlow = MutableStateFlow<Result<Pokemon>>(Result.Loading)

    val pokemonsListFlow = MutableStateFlow<Result<List<Pokemon>>>(Result.Loading)

    override fun observePokemon(): Flow<Result<Pokemon>> {
        return pokemonFlow
    }

    override fun observePokemons(): Flow<Result<List<Pokemon>>> {
        return pokemonsListFlow
    }

    override suspend fun requestPokemon(index: Int) {
        val localDataFlow = localDataSource.observeSingle(index)
        localDataFlow.collect { pokemonFlow.emit(it) }
    }

    override suspend fun requestPokemons(from: Int, to: Int) {
        val localDataFlow = localDataSource.observeRange(from, to)
        localDataFlow.collect { resultList ->
            pokemonsListFlow.emit(resultList)

            val expectedSize = to - from
            if (!rangeIsValid(resultList, expectedSize)) {
                updateLocalRangeFromRemote(from, to)
            }
        }
    }

    private fun rangeIsValid(result: Result<List<Pokemon>>?, expectedSize: Int): Boolean {
        if (result != null && !result.succeeded) {
            return false
        }
        if (result is Result.Success && result.data.size < expectedSize) {
            return false
        }

        return true
    }

    private fun updateLocalRangeFromRemote(from: Int, to: Int) {
        GlobalScope.launch {
            for (i in from .. to) {
                val remoteData = remoteDataSource.getSingle(i)
                if (remoteData is Result.Success) {
                    localDataSource.saveSingle(remoteData.data)
                } else if (remoteData is Result.Error) {
                    Log.e(TAG, "observePokemons: Remote data error: " +
                            remoteData.exception.toString(), remoteData.exception)
                }
            }
        }
    }
}