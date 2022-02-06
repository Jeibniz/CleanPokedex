package com.jeibniz.cleanpokedex.framework.data.local.di

import com.jeibniz.cleanpokedex.data.pokemon.PokemonLocalDataSource
import com.jeibniz.cleanpokedex.framework.data.local.pokemon.PokemonLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {
    @Binds
    abstract fun bindPokemonLocalDataSource(
        pokemonLocalDataSourceImpl: PokemonLocalDataSourceImpl
    ): PokemonLocalDataSource
}
