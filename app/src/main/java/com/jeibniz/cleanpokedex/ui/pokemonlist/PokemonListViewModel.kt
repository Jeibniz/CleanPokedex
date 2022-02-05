package com.jeibniz.cleanpokedex.ui.pokemonlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.jeibniz.cleanpokedex.data.Result
import com.jeibniz.cleanpokedex.ui.pokemonlist.model.PokemonListEntry
import com.jeibniz.cleanpokedex.usecases.pokemonlist.GetGenOnePokemons
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val getGenOnePokemons: GetGenOnePokemons
) : ViewModel() {

    init {
        requestGenOnePokemons()
    }

    private var _pokemons: LiveData<List<PokemonListEntry>> =
        getGenOnePokemons.observePokemons().asLiveData().switchMap { result ->
            val liveData = MutableLiveData<List<PokemonListEntry>>()
            if (result is Result.Success) {
                val mappedPokemons = result.data.map { it.toPokemonListEntry() }
                liveData.postValue(mappedPokemons)
            }

            return@switchMap liveData
        }
    val pokemons = _pokemons

    private fun requestGenOnePokemons() {
        viewModelScope.launch {
            getGenOnePokemons.requestPokemons()
        }
    }
}
