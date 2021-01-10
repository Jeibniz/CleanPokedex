package com.jeibniz.cleanpokedex.data.pokemon

import androidx.lifecycle.LiveData
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon

interface PokemonRepository {
    suspend fun getPokemon(index: Int): LiveData<Resource<Pokemon>>

    suspend fun getPokemons(from: Int, to: Int): LiveData<Resource<List<Pokemon>>>
}