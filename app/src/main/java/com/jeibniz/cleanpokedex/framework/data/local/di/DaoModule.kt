package com.jeibniz.cleanpokedex.framework.data.local.di

import com.jeibniz.cleanpokedex.framework.data.PokedexDatabase
import com.jeibniz.cleanpokedex.framework.data.local.pokemon.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {
    @Provides
    fun providePokemonDao(pokedexDatabase: PokedexDatabase): PokemonDao {
        return pokedexDatabase.getPokemonDao()
    }

}
