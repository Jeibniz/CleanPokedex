package com.jeibniz.cleanpokedex

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.jeibniz.cleanpokedex.ui.pokemondetail.PokemonDetailFragment
import com.jeibniz.cleanpokedex.ui.pokemonlist.PokemonListFragment

class PokedexFragmentFactory(
    private val viewModelFactory: ViewModelProvider.Factory
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        if (className == PokemonDetailFragment::class.java.getName()) {
            return PokemonDetailFragment(viewModelFactory)
        }
        if (className == PokemonListFragment::class.java.getName()) {
            return PokemonListFragment(viewModelFactory)
        } else {
            return super.instantiate(classLoader, className)
        }
    }
}
