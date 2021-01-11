package com.jeibniz.cleanpokedex.ui.pokemonlist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jeibniz.cleanpokedex.R

class PokemonListViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    val imageView: ImageView
    val nameView: TextView
    val numberView: TextView
    val typeView: TextView

    init {
        imageView = v.findViewById(R.id.pokemon_list_viewholder_itemImage)
        nameView = v.findViewById(R.id.pokemon_list_viewholder_itemName)
        numberView = v.findViewById(R.id.pokemon_list_viewholder_itemNumber)
        typeView = v.findViewById(R.id.pokemon_list_viewholder_itemType)
    }
}