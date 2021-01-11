package com.jeibniz.cleanpokedex.framework.data.remote.pokemon

import com.google.gson.annotations.SerializedName

data class SpriteResponse(
    @SerializedName("front_default")
    private val defaultUrl: String
)