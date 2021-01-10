package com.jeibniz.cleanpokedex.di

import android.graphics.Typeface
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.jeibniz.cleanpokedex.PokedexFragmentFactory
import com.jeibniz.cleanpokedex.ui.pokemonlist.PokemonListAdapter
import com.jeibniz.cleanpokedex.ui.pokemonlist.PokemonListViewHolder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PokedexFragmentFactoryModule {
    @Singleton
    @Provides
    fun providePokedexFragmentFactory(
        viewModelFactory: ViewModelProvider.Factory,
        pokemonListAdapter: PokemonListAdapter
    ): FragmentFactory {
        return PokedexFragmentFactory(viewModelFactory, pokemonListAdapter)
    }
}