package com.jeibniz.cleanpokedex.framework.data.remote.pokemon

import com.jeibniz.cleanpokedex.framework.data.pokemon.remote.model.GeneralPokemonRangeResponse
import com.jeibniz.cleanpokedex.framework.data.pokemon.remote.model.GeneralPokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GeneralPokemonApi {
    @Headers("Content-Type: application/json")
    @GET("pokemon/{index}")
    fun getSingle(@Path("index") index: Int): Call<GeneralPokemonResponse>



    @Headers("Content-Type: application/json")
    @GET("pokemon")
    fun getRange(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int): Call<GeneralPokemonRangeResponse>

}