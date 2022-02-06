package com.jeibniz.cleanpokedex.data.pokemon

import android.util.Log
import com.jeibniz.cleanpokedex.data.ErrorResult
import com.jeibniz.cleanpokedex.data.LoadingResult
import com.jeibniz.cleanpokedex.data.Result
import com.jeibniz.cleanpokedex.data.SuccessResult
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val localDataSource: PokemonLocalDataSource,
    private val remoteDataSource: PokemonRemoteDataSource,
) : PokemonRepository {

    override suspend fun getPokemons(from: Int, to: Int) = flow {
        emit(LoadingResult)
        localDataSource.getRange(from, to).collect { localData ->
            val numbersLoaded = localData.map { it.number } // TODO add if old
            val numbersToLoad = (from..to).filter { !numbersLoaded.contains(it) }
            val remoteData = updateLocalFromRemote(numbersToLoad)

            if (localData.isEmpty()) {
                if (remoteData is SuccessResult && remoteData.data.isNotEmpty()) {
                    emit(LoadingResult)
                } else {
                    emit(ErrorResult<List<Pokemon>>("Could not load data from API"))
                }
            } else {
                emit(SuccessResult(localData))
            }
        }

    }.flowOn(Dispatchers.IO)

    private suspend fun updateLocalFromRemote(pokemonNumbers: List<Int>): Result<List<Pokemon>> {
        val remoteData = remoteDataSource.getList(pokemonNumbers)
        if (remoteData is SuccessResult) {
            localDataSource.saveList(remoteData.data)
        } else if (remoteData is ErrorResult) {
            Timber.e(
                javaClass.simpleName,
                "observePokemons: Remote data error: ${remoteData.errorMessage.toString()}"
            )
        }
        return remoteData
    }

    override suspend fun getPokemon(number: Int) = flow {
        emit(LoadingResult)
        localDataSource.getSingle(number).collect {
            emit(SuccessResult(it))
        }
    }.flowOn(Dispatchers.IO)
}
