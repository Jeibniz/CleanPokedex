package com.jeibniz.cleanpokedex.ui.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeibniz.cleanpokedex.data.LoadingResult
import com.jeibniz.cleanpokedex.data.Result
import com.jeibniz.cleanpokedex.data.map
import com.jeibniz.cleanpokedex.mappers.toPokemonListEntry
import com.jeibniz.cleanpokedex.ui.pokemonlist.model.PokemonListEntry
import com.jeibniz.cleanpokedex.usecases.pokemonlist.GetGenOnePokemons
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getGenOnePokemons: GetGenOnePokemons
) : ViewModel() {

    private val _pokemonList: MutableStateFlow<Result<List<PokemonListEntry>>> = MutableStateFlow(LoadingResult)
    val pokemonList = _pokemonList

    init {
        requestPokemons()
    }

    fun requestPokemons() {
        viewModelScope.launch {
            getGenOnePokemons.getPokemons().collect { result ->
                _pokemonList.emit(result.map { data -> data.map { it.toPokemonListEntry() } })
            }
        }
    }
}
