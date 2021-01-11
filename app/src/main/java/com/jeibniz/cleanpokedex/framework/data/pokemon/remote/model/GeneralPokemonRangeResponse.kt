package com.jeibniz.cleanpokedex.framework.data.pokemon.remote.model

import com.google.gson.annotations.SerializedName

data class GeneralPokemonRangeResponse(
    @SerializedName("results") val pokemons: List<GeneralPokemonResponse>,
)
