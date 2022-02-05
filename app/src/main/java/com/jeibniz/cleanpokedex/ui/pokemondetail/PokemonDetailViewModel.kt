package com.jeibniz.cleanpokedex.ui.pokemondetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.jeibniz.cleanpokedex.data.Result
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import com.jeibniz.cleanpokedex.usecases.pokemondetail.GetPokemon
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val getPokemon: GetPokemon
) : ViewModel() {

    private val _pokemon = getPokemon.observePokemon().asLiveData().switchMap { result ->
        val liveData = MutableLiveData<Pokemon>()
        if (result is Result.Success) {
            liveData.postValue(result.data)
        }

        return@switchMap liveData
    }
    val pokemon: LiveData<Pokemon> = _pokemon

    fun requestPokemon(number: Int) {
        viewModelScope.launch {
            getPokemon.requestPokemon(number)
        }
    }
}
