package com.jeibniz.cleanpokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jeibniz.cleanpokedex.ui.pokemonlist.PokemonListViewModel
import com.jeibniz.cleanpokedex.usecases.pokemonlist.GetGenOnePokemons

class PokedexViewModelFactory(
    private  val getGenOnePokemons: GetGenOnePokemons
) : ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == PokemonListViewModel::class.java) {
            return PokemonListViewModel(getGenOnePokemons) as T
        } else {
            throw IllegalArgumentException(String.format("Unknown model class %s", modelClass))
        }
    }
}