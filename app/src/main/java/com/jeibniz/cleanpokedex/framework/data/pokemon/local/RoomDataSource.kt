package com.jeibniz.cleanpokedex.framework.data.local.pokemon

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jeibniz.cleanpokedex.data.Resource
import com.jeibniz.cleanpokedex.data.pokemon.PokemonLocalDataSource
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon

class RoomDataSource(
    context: Context
) : PokemonLocalDataSource {

    private val tempObservable=  MutableLiveData<Resource<List<Pokemon>>>()

    override fun observeRange(from: Int, to: Int): LiveData<Resource<List<Pokemon>>> {

        val bulbasar = Pokemon("Bulbasar", 1, "A pokemon", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            listOf("Grass","Poision"), 3 ,5)

        val charmander = Pokemon("Charmander", 4,"A pokemon", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png",
            listOf("fire"), 3 ,5)

        tempObservable.postValue(Resource.success(listOf(bulbasar, charmander)))

        return tempObservable
    }

    override fun observeSingle(index: Int): LiveData<Resource<Pokemon>> {
        TODO("Not yet implemented")
    }

    override fun saveRange(data: List<Pokemon>) {
        TODO("Not yet implemented")
    }

    override fun saveSingle(data: Pokemon) {
        TODO("Not yet implemented")
    }
}