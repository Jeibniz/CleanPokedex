package com.jeibniz.cleanpokedex.ui.pokemonlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.jeibniz.cleanpokedex.R

class PokemonListFragment(
    private val viewModelFactory: ViewModelProvider.Factory,
    private val adapter: PokemonListAdapter
) : Fragment() {
    
    private lateinit var viewModel: PokemonListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.pokemon_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =  ViewModelProvider(this, viewModelFactory).get(PokemonListViewModel::class.java)
        initUiComponents(view)
        subscribeObservers()
    }

    private fun initUiComponents(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.fragment_list_recyclerview)



        recyclerView.adapter = adapter
        //recyclerView.adapter(ArrayAdapter<String>(this, R.style.))

    }

    private fun subscribeObservers() {
        // Ensure that we do not subscribe multiple times.
        /*
        viewModel.observePairing().removeObservers(viewLifecycleOwner)
        viewModel.observePairing().observe(
            viewLifecycleOwner
        ) { userAuthResource: AuthResource<String?>? -> this.onPairedStateChanged(userAuthResource) }

         */
    }


}