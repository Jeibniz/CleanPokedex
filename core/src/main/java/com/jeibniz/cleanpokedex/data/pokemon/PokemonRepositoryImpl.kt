package com.jeibniz.cleanpokedex.data.pokemon

import android.util.Log
import com.jeibniz.cleanpokedex.data.ErrorResult
import com.jeibniz.cleanpokedex.data.LoadingResult
import com.jeibniz.cleanpokedex.data.Result
import com.jeibniz.cleanpokedex.data.SuccessResult
import com.jeibniz.cleanpokedex.data.succeeded
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val localDataSource: PokemonLocalDataSource,
    private val remoteDataSource: PokemonRemoteDataSource,
) : PokemonRepository {

    private val TAG = "PokemonRepositoryImpl"

    private val pokemonFlow = MutableStateFlow<Result<Pokemon>>(LoadingResult)

    override suspend fun getPokemons(from: Int, to: Int) = flow {
        emit(LoadingResult)
        val localDataFlow = localDataSource.observeRange(from, to)
        localDataFlow.collect { resultList ->
            emit(resultList)

            val expectedSize = to - from
            if (!rangeIsValid(resultList, expectedSize)) {
                updateLocalRangeFromRemote(from, to)
            }
        }
    }.flowOn(Dispatchers.IO)

    private fun rangeIsValid(result: Result<List<Pokemon>>?, expectedSize: Int): Boolean {
        if (result != null && !result.succeeded) {
            return false
        }
        if (result is SuccessResult && result.data.size < expectedSize) {
            return false
        }

        return true
    }

    private suspend fun updateLocalRangeFromRemote(from: Int, to: Int) {
        for (i in from..to) {
            val remoteData = remoteDataSource.getSingle(i)
            if (remoteData is SuccessResult) {
                localDataSource.saveSingle(remoteData.data)
            } else if (remoteData is ErrorResult) {
                Log.e(
                    TAG,
                    "observePokemons: Remote data error: " + remoteData.errorMessage.toString()
                )
            }
        }
    }

    override suspend fun getPokemon(number: Int) = flow {
        emit(LoadingResult)
        val localDataFlow = localDataSource.observeSingle(number)
        localDataFlow.collect { emit(it) }
    }.flowOn(Dispatchers.IO)
}
