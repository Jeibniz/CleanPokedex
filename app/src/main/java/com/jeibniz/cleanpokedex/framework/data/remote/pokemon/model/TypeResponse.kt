package com.jeibniz.cleanpokedex.framework.data.pokemon.remote.model

import com.google.gson.annotations.SerializedName

data class TypesResponse(
    val type: TypeResponse
)

data class TypeResponse(
    val name: String
)