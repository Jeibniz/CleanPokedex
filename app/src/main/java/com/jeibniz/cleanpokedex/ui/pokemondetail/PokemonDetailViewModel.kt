package com.jeibniz.cleanpokedex.ui.pokemondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeibniz.cleanpokedex.data.LoadingResult
import com.jeibniz.cleanpokedex.data.Result
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import com.jeibniz.cleanpokedex.usecases.pokemondetail.GetPokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemon: GetPokemon
) : ViewModel() {

    private val _pokemonDetails: MutableStateFlow<Result<Pokemon>> = MutableStateFlow(LoadingResult)
    val pokemonDetails: StateFlow<Result<Pokemon>> = _pokemonDetails

    fun requestPokemon(number: Int) {
        viewModelScope.launch {
            getPokemon.getPokemon(number).collect {
                _pokemonDetails.emit(it)
            }
        }
    }
}
