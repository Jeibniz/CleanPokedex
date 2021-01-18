package com.jeibniz.cleanpokedex.ui.pokemondetail

import androidx.lifecycle.*
import com.jeibniz.cleanpokedex.data.Resource
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import com.jeibniz.cleanpokedex.ui.pokemonlist.toPokemonListEntry
import com.jeibniz.cleanpokedex.usecases.pokemondetail.GetPokemon

class PokemonDetailViewModel(
    getPokemon: GetPokemon
) : ViewModel() {

    private val pokemonNumber = MutableLiveData<Int>()

    private val pokemon = pokemonNumber.switchMap { number ->
        getPokemon(number).map { it }
    }

    fun start(number: Int) {
        if (number == pokemonNumber.value) {
            return
        }
        pokemonNumber.value = number
    }

    fun observePokemon() = pokemon

}