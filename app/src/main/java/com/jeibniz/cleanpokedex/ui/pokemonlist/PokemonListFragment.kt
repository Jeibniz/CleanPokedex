package com.jeibniz.cleanpokedex.ui.pokemonlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jeibniz.cleanpokedex.R

class PokemonListFragment(
    private val viewModelFactory: ViewModelProvider.Factory,
) : Fragment() {
    
    private lateinit var viewModel: PokemonListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =  ViewModelProvider(this, viewModelFactory).get(PokemonListViewModel::class.java)
        initUiComponents(view)
    }

    private fun initUiComponents(view: View) {

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokemonListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}