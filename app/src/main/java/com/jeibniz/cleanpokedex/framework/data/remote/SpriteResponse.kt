package com.jeibniz.cleanpokedex.framework.data.remote

import com.google.gson.annotations.SerializedName

data class SpriteResponse(
    @SerializedName("front_default")
    private val defaultUrl: String
)