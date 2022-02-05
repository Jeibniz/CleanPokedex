package com.jeibniz.cleanpokedex.framework.data.pokemon.remote.model

import com.google.gson.annotations.SerializedName

data class SpriteResponse(
    @SerializedName("front_default")
    val defaultUrl: String
)
