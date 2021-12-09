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

    private var _pokemons: LiveData<Result<List<PokemonListEntry>>> =
        getGenOnePokemons.observePokemons().asLiveData().switchMap { results ->
            MutableLiveData(results.map { pokemons -> pokemons.map { it.toPokemonListEntry() } })
        }
    val pokemons = _pokemons

    private fun requestGenOnePokemons() {
        viewModelScope.launch {
            getGenOnePokemons.requestPokemons()
        }
    }

}