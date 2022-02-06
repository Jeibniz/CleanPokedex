package com.jeibniz.cleanpokedex.data.di

import com.jeibniz.cleanpokedex.data.pokemon.PokemonRepository
import com.jeibniz.cleanpokedex.data.pokemon.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RetrofitModule {
    @Binds
    abstract fun bindPokemonRepository(
        pokemonRepositoryImpl: PokemonRepositoryImpl
    ): PokemonRepository
}

