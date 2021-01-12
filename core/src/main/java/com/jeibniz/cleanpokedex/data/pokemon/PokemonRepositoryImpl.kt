package com.jeibniz.cleanpokedex.data.pokemon

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.jeibniz.cleanpokedex.data.Resource
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PokemonRepositoryImpl(
    private val localDataSource: PokemonLocalDataSource,
    private val remoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {

    private val TAG = "PokemonRepositoryImpl"

    override fun observePokemon(index: Int): LiveData<Resource<Pokemon>> {
        return localDataSource.observeSingle(index).asLiveData()
    }

    override fun observePokemons(from: Int, to: Int): LiveData<Resource<List<Pokemon>>> {
        val localLiveData = localDataSource.observeRange(from, to).asLiveData()
        val expectedSize = to - from

        if (!rangeIsValid(localLiveData.value, expectedSize)) {
            updateLocalRangeFromRemote(from, to)
        }

        return localLiveData
    }


    private fun rangeIsValid(resource: Resource<List<Pokemon>>?, expectedSize: Int): Boolean {
        if (resource?.data == null) {
            return false
        }
        if (resource.data.size < expectedSize) {
            return false
        }

        return true
    }

    private fun updateLocalRangeFromRemote(from: Int, to: Int) {
        GlobalScope.launch {
            val remoteData = remoteDataSource.getRange(from, to)
            if (remoteData.status == Resource.Status.SUCCESS) {
                localDataSource.saveRange(remoteData.data!!)
            } else if (remoteData.status == Resource.Status.ERROR) {
                Log.e(TAG, "observePokemons: Remote data error: " + remoteData.throwable.toString(), remoteData.throwable)

            }
        }
    }


}