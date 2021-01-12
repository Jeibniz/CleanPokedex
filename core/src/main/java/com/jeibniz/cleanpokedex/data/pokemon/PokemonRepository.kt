package com.jeibniz.cleanpokedex.data.pokemon

import androidx.lifecycle.LiveData
import com.jeibniz.cleanpokedex.data.Resource
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun observePokemon(index: Int): LiveData<Resource<Pokemon>>

    fun observePokemons(from: Int, to: Int): LiveData<Resource<List<Pokemon>>>
}