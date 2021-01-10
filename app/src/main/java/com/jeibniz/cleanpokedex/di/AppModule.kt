package com.jeibniz.cleanpokedex.di

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.jeibniz.cleanpokedex.ui.pokemonlist.PokemonListAdapter
import com.jeibniz.cleanpokedex.ui.pokemonlist.PokemonListViewHolder
import com.jeibniz.cleanpokedex.ui.pokemonlist.model.PokemonListEntry
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun providePokemonListAdapter(): PokemonListAdapter {
        return PokemonListAdapter(emptyList<PokemonListEntry>())
    }
}