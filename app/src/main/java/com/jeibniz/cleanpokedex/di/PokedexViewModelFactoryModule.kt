package com.jeibniz.cleanpokedex.di

import androidx.lifecycle.ViewModelProvider
import com.jeibniz.cleanpokedex.PokedexViewModelFactory
import com.jeibniz.cleanpokedex.usecases.pokemonlist.GetGenOnePokemons
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PokedexViewModelFactoryModule {
    @Singleton
    @Provides
    fun providePokedexViewModelFactory(getGenOnePokemons: GetGenOnePokemons): ViewModelProvider.Factory {
        return PokedexViewModelFactory(getGenOnePokemons)
    }
}