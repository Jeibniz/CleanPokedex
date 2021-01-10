package com.jeibniz.cleanpokedex

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.jeibniz.cleanpokedex.ui.pokemonlist.PokemonListAdapter
import com.jeibniz.cleanpokedex.ui.pokemonlist.PokemonListFragment
import com.jeibniz.cleanpokedex.ui.pokemonlist.PokemonListViewHolder

class PokedexFragmentFactory(
    private val viewModelFactory: ViewModelProvider.Factory,
    private val pokemonListAdapter: PokemonListAdapter
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        if (className == PokemonListFragment::class.java.getName()) {
            return PokemonListFragment(viewModelFactory, pokemonListAdapter)
        } else {
            return super.instantiate(classLoader, className)
        }
    }
}