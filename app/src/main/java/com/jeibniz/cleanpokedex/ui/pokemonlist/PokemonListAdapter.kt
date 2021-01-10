package com.jeibniz.cleanpokedex.ui.pokemonlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeibniz.cleanpokedex.R
import com.jeibniz.cleanpokedex.ui.pokemonlist.model.PokemonListEntry
import com.squareup.picasso.Picasso

class PokemonListAdapter(
    private var pokemons: List<PokemonListEntry> = emptyList<PokemonListEntry>()) :
        RecyclerView.Adapter<PokemonListViewHolder>() {

    fun setPokemons(pokemons: List<PokemonListEntry>) {
        this.pokemons = pokemons
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
            .placeholder(R.drawable.pokemon_placeholder)
            .error(R.drawable.pokemon_error_placeholder)
            .into(holder.imageView);

        holder.nameView.text = pokemons[position].name
        holder.descriptionView.text = pokemons[position].description
    }

    override fun getItemCount(): Int {
        return pokemons.count()
    }
}