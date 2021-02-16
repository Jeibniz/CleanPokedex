package com.jeibniz.cleanpokedex.framework.data.remote.pokemon.model

import com.google.gson.annotations.SerializedName

data class DetailedPokemonResponse(
   @SerializedName("flavor_text_entries") val descriptions: List<PokemonDescription>
)