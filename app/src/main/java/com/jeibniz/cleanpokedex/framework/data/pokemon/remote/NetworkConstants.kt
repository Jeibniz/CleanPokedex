package com.jeibniz.cleanpokedex.framework.data.pokemon.remote

import java.util.concurrent.TimeUnit

object NetworkConstants {
    const val BASE_URL = "https://pokeapi.co/api/v2/"

    @JvmField
    val TIME_UNIT = TimeUnit.SECONDS
    const val CALL_TIMEOUT = 20;
    const val READ_TIMEOUT = 20
    const val WRITE_TIMEOUT = 20
}