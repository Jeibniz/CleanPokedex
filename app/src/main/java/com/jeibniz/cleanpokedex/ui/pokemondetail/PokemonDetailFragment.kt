package com.jeibniz.cleanpokedex.ui.pokemondetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.jeibniz.cleanpokedex.R
import com.jeibniz.cleanpokedex.ui.pokemonlist.PokemonListViewModel

class PokemonDetailFragment(
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment() {

    private val TAG = "PokemonDetailFragment"
    val args: PokemonDetailFragmentArgs by navArgs()

    lateinit var imageView: ImageView
    lateinit var nameView: TextView
    lateinit var numberView: TextView
    lateinit var descriptionView: TextView
    lateinit var heightView: TextView
    lateinit var weightView: TextView

    private lateinit var viewModel: PokemonDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pokemon_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =  ViewModelProvider(this, viewModelFactory).get(PokemonDetailViewModel::class.java)
        Log.d(TAG, "onViewCreated: " + args.pokemonNumber)
        initUiComponents(view)
        //subscribeObservers()
    }

    private fun initUiComponents(view: View) {
        imageView = view.findViewById(R.id.fragment_detail_imageView)
        nameView = view.findViewById(R.id.fragment_detail_name)
        numberView = view.findViewById(R.id.fragment_detail_number)
        descriptionView = view.findViewById(R.id.fragment_detail_description)
        heightView = view.findViewById(R.id.fragment_detail_height)
        weightView = view.findViewById(R.id.fragment_detail_weight)
    }


}