package com.jeibniz.cleanpokedex.di

import android.content.Context
import com.jeibniz.cleanpokedex.BaseApplication
import com.jeibniz.cleanpokedex.data.pokemon.PokemonLocalDataSource
import com.jeibniz.cleanpokedex.data.pokemon.PokemonRemoteDataSource
import com.jeibniz.cleanpokedex.data.pokemon.PokemonRepository
import com.jeibniz.cleanpokedex.data.pokemon.PokemonRepositoryImpl
import com.jeibniz.cleanpokedex.framework.data.local.pokemon.RoomDataSource
import com.jeibniz.cleanpokedex.framework.data.remote.RetrofitDataSource
import com.jeibniz.cleanpokedex.ui.pokemonlist.PokemonListAdapter
import com.jeibniz.cleanpokedex.ui.pokemonlist.model.PokemonListEntry
import com.jeibniz.cleanpokedex.usecases.pokemonlist.GetGenOnePokemons
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(baseApplication: BaseApplication): Context {
        return baseApplication.getBaseContext()
    }

    @Provides
    fun providePokemonRepository(
        pokemonLocalDataSource: PokemonLocalDataSource,
        pokemonRemoteDataSource: PokemonRemoteDataSource): PokemonRepository {
        return PokemonRepositoryImpl(pokemonLocalDataSource, pokemonRemoteDataSource)
    }

    @Provides
    fun providePokemonRemoteDataSource(): PokemonRemoteDataSource {
        return RetrofitDataSource()
    }

    @Provides
    fun providePokemonLocalDataSource(context: Context): PokemonLocalDataSource {
        return RoomDataSource(context)
    }

    @Provides
    fun provideGetGenOnePokemons(pokemonRepository: PokemonRepository): GetGenOnePokemons {
        return GetGenOnePokemons(pokemonRepository)
    }

    @Provides
    fun providePokemonListAdapter(): PokemonListAdapter {
        return PokemonListAdapter(emptyList())
    }
}