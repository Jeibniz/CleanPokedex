package com.jeibniz.cleanpokedex.framework.data.remote.pokemon

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

internal interface PokemonApi {
    @Headers("Content-Type: application/json")
    @GET("pokemon/{index}")
    fun getSingle(@Path("index") index: Int): Call<SinglePokemonResponse>
}