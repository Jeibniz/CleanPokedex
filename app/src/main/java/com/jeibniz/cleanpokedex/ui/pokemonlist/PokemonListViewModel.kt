package com.jeibniz.cleanpokedex.ui.pokemonlist

import androidx.lifecycle.*
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