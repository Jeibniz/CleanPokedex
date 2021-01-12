package com.jeibniz.cleanpokedex.framework.data.remote.pokemon

import android.util.Log
import com.jeibniz.cleanpokedex.data.pokemon.PokemonRemoteDataSource
import com.jeibniz.cleanpokedex.data.Resource
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import com.jeibniz.cleanpokedex.framework.data.pokemon.remote.NetworkConstants
import com.jeibniz.cleanpokedex.mappers.toPokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class RetrofitDataSource(
    retrofit: Retrofit): PokemonRemoteDataSource {

    private val TAG = "RetrofitDataSource"

    val generalPokemonApi: GeneralPokemonApi = retrofit.create(GeneralPokemonApi::class.java)

    override suspend fun getRange(from: Int, to: Int): Resource<List<Pokemon>> {
        val resultList = mutableListOf<Pokemon>()
        try {
            for (i in 0..to - from) {
                val pokemonIndex = from + i
                resultList.add(i, getSinglePokemon(pokemonIndex))
                Log.d(TAG, "getRange: " + resultList.get(i).name)
            }
        } catch (exception: IOException) {
            return Resource.error(exception)
        }

        return  Resource.success(resultList)
    }

    override suspend fun getSingle(index: Int): Resource<Pokemon> {
        try {
            return Resource.success(getSinglePokemon(index))
        } catch (exception: Exception) {
            return Resource.error(exception)
        }
    }

    private suspend fun getSinglePokemon(index: Int) : Pokemon {
        val apiCall = generalPokemonApi.getSingle(index)

        //val apiResponse = withContext(Dispatchers.IO) { apiCall.execute() }
        val apiResponse = apiCall.execute()
        Log.d(TAG, "getSinglePokemon: calling url: " + apiCall.request())
        apiCall.request().url()
        if (!apiResponse.isSuccessful || apiResponse.body() == null) {
            throw IOException("Unsuccessful retrofit call")
        }
        return apiResponse.body()!!.toPokemon()
    }

}