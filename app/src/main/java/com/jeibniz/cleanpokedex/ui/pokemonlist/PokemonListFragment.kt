package com.jeibniz.cleanpokedex.ui.pokemonlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeibniz.cleanpokedex.R
import com.jeibniz.cleanpokedex.data.Resource
import com.jeibniz.cleanpokedex.ui.pokemonlist.model.PokemonListEntry

class PokemonListFragment(
    private val viewModelFactory: ViewModelProvider.Factory,
    private val adapter: PokemonListAdapter
) : Fragment() {
    private val TAG = "PokemonListFragment"
    
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
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), R.drawable.divider))
        recyclerView.adapter = adapter
    }

    private fun subscribeObservers() {
        viewModel.observePokemons().observe(
            viewLifecycleOwner) {
            onDataChanged(it)
        }
    }

    private fun onDataChanged(resource: Resource<List<PokemonListEntry>>) {
        Log.d(TAG, "onDataChanged: " + resource.status)
        if (resource.status == Resource.Status.SUCCESS) {
           adapter.setPokemons(resource.data!!)
        } else if (resource.status == Resource.Status.ERROR) {
            throw resource.throwable!!
        }
    }
}