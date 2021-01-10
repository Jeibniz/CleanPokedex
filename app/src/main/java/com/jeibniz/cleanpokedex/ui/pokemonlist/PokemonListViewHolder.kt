package com.jeibniz.cleanpokedex.ui.pokemonlist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jeibniz.cleanpokedex.R

class PokemonListViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    val imageView: ImageView
    val nameView: TextView
    val descriptionView: TextView

    init {
        imageView = v.findViewById(R.id.pokemon_list_viewholder_itemImage)
        nameView = v.findViewById(R.id.pokemon_list_viewholder_itemName)
        descriptionView = v.findViewById(R.id.pokemon_list_viewholder_itemDescription)
    }
}