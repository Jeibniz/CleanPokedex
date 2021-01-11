package com.jeibniz.cleanpokedex.data.pokemon

import androidx.lifecycle.LiveData
import com.jeibniz.cleanpokedex.data.Resource
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon

interface PokemonLocalDataSource {

    fun observeRange(from: Int, to: Int) : LiveData<Resource<List<Pokemon>>>

    fun observeSingle(index: Int) : LiveData<Resource<Pokemon>>

    fun saveRange(data: List<Pokemon>)

    fun saveSingle(data: Pokemon)

}