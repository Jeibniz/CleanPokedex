package com.jeibniz.cleanpokedex.ui.pokemonlist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jeibniz.cleanpokedex.R
import com.jeibniz.cleanpokedex.ui.pokemonlist.model.PokemonListEntry
import com.squareup.picasso.Picasso

class PokemonListAdapter(
            private var pokemons: List<PokemonListEntry> = emptyList<PokemonListEntry>()
) : RecyclerView.Adapter<PokemonListViewHolder>() {

    private val TAG = "PokemonListAdapter"

    fun setPokemons(pokemons: List<PokemonListEntry>) {
        Log.v(TAG, "setPokemons: new list with size " + pokemons.size)
        this.pokemons = pokemons
        notifyItemInserted(0)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        // Setting attach to root to false.
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_list_fragment_view_holder, parent, false)
        return PokemonListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        holder.nameView.text = pokemons[position].name

        Picasso.with(holder.imageView.context).load(pokemons[position].imageUrl).fit().centerCrop()
            .error(R.drawable.pokemon_error_placeholder)
            .into(holder.imageView);

        holder.nameView.text = pokemons[position].name
        holder.numberView.text = pokemons[position].number.toString().padStart(3, '0')
        holder.typeView.text = pokemons[position].types.reduce { acc, s -> String.format("%s %s", acc, s) }

        holder.container.setOnClickListener {
            val action = PokemonListFragmentDirections
                .actionPokemonListFragmentToPokemonDetailsFragment(pokemons[position].number)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return pokemons.count()
    }
}