package com.jeibniz.cleanpokedex.ui.pokemondetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs

class PokemonDetailFragment(
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment() {

    private val TAG = "PokemonDetailFragment"
    private val args: PokemonDetailFragmentArgs by navArgs()

    private lateinit var viewModel: PokemonDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                val pokemon by viewModel.pokemon.observeAsState()
                pokemon?.let { PokemonDetailsScreen(it) }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[PokemonDetailViewModel::class.java]
        viewModel.requestPokemon(args.pokemonNumber)
        Log.d(TAG, "onViewCreated: " + args.pokemonNumber)
    }
}
