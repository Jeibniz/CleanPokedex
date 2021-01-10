package com.jeibniz.cleanpokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jeibniz.cleanpokedex.ui.pokemonlist.PokemonListViewModel

class PokedexViewModelFactory : ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == PokemonListViewModel::class.java) {
            return PokemonListViewModel() as T
        } else {
            throw IllegalArgumentException(String.format("Unknown model class %s", modelClass))
        }
    }
}