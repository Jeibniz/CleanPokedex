package com.jeibniz.cleanpokedex.framework.data.remote.pokemon

import android.util.Log
import com.jeibniz.cleanpokedex.data.ErrorResult
import com.jeibniz.cleanpokedex.data.Result
import com.jeibniz.cleanpokedex.data.SuccessResult
import com.jeibniz.cleanpokedex.data.pokemon.PokemonRemoteDataSource
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import com.jeibniz.cleanpokedex.framework.data.remote.pokemon.model.DescriptionLanguage
import com.jeibniz.cleanpokedex.framework.data.remote.pokemon.model.PokemonDescription
import com.jeibniz.cleanpokedex.mappers.toPokemon
import com.jeibniz.cleanpokedex.utils.TextUtils
import java.io.IOException
import javax.inject.Inject
import retrofit2.Retrofit

class PokemonRemoteDataSourceImpl @Inject constructor(
    private val generalPokemonApi: GeneralPokemonApi,
    private val detailedPokemonApi: DetailedPokemonApi
) : PokemonRemoteDataSource {

    private val TAG = "RetrofitDataSource"


    override suspend fun getRange(from: Int, to: Int): Result<List<Pokemon>> {
        val resultList = mutableListOf<Pokemon>()
        try {
            for (i in 0..to - from) {
                val pokemonIndex = from + i
                resultList.add(i, getSinglePokemon(pokemonIndex))
                Log.d(TAG, "getRange: " + resultList.get(i).name)
            }
        } catch (exception: IOException) {
            return ErrorResult(exception)
        }

        return SuccessResult(resultList)
    }

    override suspend fun getSingle(index: Int): Result<Pokemon> {
        return try {
            SuccessResult(getSinglePokemon(index))
        } catch (exception: Exception) {
            ErrorResult(exception)
        }
    }

    private fun getSinglePokemon(index: Int): Pokemon {
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
        return detailedApiResponse.body()!!.descriptions
    }

    private fun findFirstEnglishDescription(descriptions: List<PokemonDescription>): String {
        var firstDescription = ""
        for (i in 0..descriptions.count()) {
            if (descriptions[i].language.name == DescriptionLanguage.englishIdentifier) {
                firstDescription = descriptions[i].description
                break
            }
        }
        return firstDescription
    }
}
