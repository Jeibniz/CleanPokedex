package com.jeibniz.cleanpokedex.ui.pokemonlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment(
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment() {
    private lateinit var viewModel: PokemonListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this, viewModelFactory).get(PokemonListViewModel::class.java)
        return ComposeView(requireContext()).apply {
            setContent {
                val pokemons by viewModel.pokemons.observeAsState()
                pokemons?.let {
                    PokemonListScreen(
                        it,
                        onEvent = ::onListItemClicked
                    )
                }
            }
        }
    }

    private fun onListItemClicked(event: PokemonListEvent) {
        when (event) {
            is PokemonListEvent.NavigateToDetails -> navigateToDetails(event.itemId)
        }
    }

    private fun navigateToDetails(itemId: Int) {
        val action = PokemonListFragmentDirections
            .actionPokemonListFragmentToPokemonDetailsFragment()
        findNavController().navigate(action)
    }
}
