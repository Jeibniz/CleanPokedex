package com.jeibniz.cleanpokedex.ui.pokemonlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.jeibniz.cleanpokedex.data.Resource
import com.jeibniz.cleanpokedex.ui.pokemonlist.model.PokemonListEntry
import com.jeibniz.cleanpokedex.usecases.pokemonlist.GetGenOnePokemons

class PokemonListViewModel(
    getGenOnePokemons: GetGenOnePokemons
) : ViewModel() {

    private var pokemons: LiveData<Resource<List<PokemonListEntry>>> =
        Transformations.switchMap(getGenOnePokemons()) {
            MutableLiveData(it.map { it.map { it.toPokemonListEntry() } })
        }

    fun observePokemons()  = pokemons

}