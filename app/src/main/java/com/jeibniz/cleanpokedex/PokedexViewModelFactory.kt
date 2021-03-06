package com.jeibniz.cleanpokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jeibniz.cleanpokedex.ui.pokemondetail.PokemonDetailViewModel
import com.jeibniz.cleanpokedex.ui.pokemonlist.PokemonListViewModel
import com.jeibniz.cleanpokedex.usecases.pokemondetail.GetPokemon
import com.jeibniz.cleanpokedex.usecases.pokemonlist.GetGenOnePokemons

class PokedexViewModelFactory(
    private val getGenOnePokemons: GetGenOnePokemons,
    private val getPokemon: GetPokemon
) : ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == PokemonListViewModel::class.java) {
            return PokemonListViewModel(getGenOnePokemons) as T
        }
        if (modelClass == PokemonDetailViewModel::class.java) {
            return PokemonDetailViewModel(getPokemon) as T
        } else {
            throw IllegalArgumentException(String.format("Unknown model class %s", modelClass))
        }
    }
}