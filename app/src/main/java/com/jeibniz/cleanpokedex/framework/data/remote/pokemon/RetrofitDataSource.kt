package com.jeibniz.cleanpokedex.framework.data.remote.pokemon

import android.util.Log
import com.jeibniz.cleanpokedex.data.pokemon.PokemonRemoteDataSource
import com.jeibniz.cleanpokedex.data.Resource
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import com.jeibniz.cleanpokedex.framework.data.pokemon.remote.NetworkConstants
import com.jeibniz.cleanpokedex.framework.data.pokemon.remote.model.GeneralPokemonResponse
import com.jeibniz.cleanpokedex.framework.data.remote.pokemon.model.DescriptionLanguage
import com.jeibniz.cleanpokedex.framework.data.remote.pokemon.model.PokemonDescription
import com.jeibniz.cleanpokedex.mappers.toPokemon
import com.jeibniz.cleanpokedex.utils.TextUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
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
    val detailedPokemonApi: DetailedPokemonApi = retrofit.create(DetailedPokemonApi::class.java)

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

    private fun getSinglePokemon(index: Int) : Pokemon {
        val pokemon = getGeneralPokemon(index)
        pokemon.description = getFirstEnglishPokemonDescription(index)

        return pokemon
    }

    private fun getGeneralPokemon(index: Int): Pokemon {
        val apiCall = generalPokemonApi.getSingle(index)
        val apiResponse = apiCall.execute()
        Log.d(TAG, "getGeneralPokemon: calling url: " + apiCall.request())
        apiCall.request().url()
        if (!apiResponse.isSuccessful || apiResponse.body() == null) {
            throw IOException("Unsuccessful retrofit call")
        }

        return apiResponse.body()!!.toPokemon()
    }

    private fun getFirstEnglishPokemonDescription(index: Int): String {
        val descriptions = getDescriptions(index)
        val firstDescription = findFirstEnglishDescription(descriptions)
        return TextUtils.removeNewLine(firstDescription)
    }

    private fun getDescriptions(index: Int): List<PokemonDescription> {
        val detailedApiCall = detailedPokemonApi.getSingle(index)

        val detailedApiResponse = detailedApiCall.execute()
        Log.d(TAG, "getPokemonDescription: calling url: " + detailedApiCall.request())
        detailedApiCall.request().url()
        if (!detailedApiResponse.isSuccessful || detailedApiResponse.body() == null) {
            throw IOException("Unsuccessful retrofit call")
        }
        val descriptions = detailedApiResponse.body()!!.descriptions
        return descriptions
    }

    private fun findFirstEnglishDescription(descriptions: List<PokemonDescription>): String {
        var firstDescription = ""
        for (i in 0..descriptions.count()) {
            if (descriptions.get(i).language.name.equals(DescriptionLanguage.englishIdentifier)) {
                firstDescription = descriptions.get(i).description
                break
            }
        }
        return firstDescription
    }


}