package com.jeibniz.cleanpokedex.di

import android.content.Context
import com.jeibniz.cleanpokedex.BaseApplication
import com.jeibniz.cleanpokedex.data.pokemon.PokemonLocalDataSource
import com.jeibniz.cleanpokedex.data.pokemon.PokemonRemoteDataSource
import com.jeibniz.cleanpokedex.data.pokemon.PokemonRepository
import com.jeibniz.cleanpokedex.data.pokemon.PokemonRepositoryImpl
import com.jeibniz.cleanpokedex.framework.data.local.pokemon.RoomDataSource
import com.jeibniz.cleanpokedex.framework.data.pokemon.remote.NetworkConstants
import com.jeibniz.cleanpokedex.framework.data.remote.pokemon.RetrofitDataSource
import com.jeibniz.cleanpokedex.ui.pokemonlist.PokemonListAdapter
import com.jeibniz.cleanpokedex.usecases.pokemonlist.GetGenOnePokemons
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideContext(baseApplication: BaseApplication): Context {
        return baseApplication.getBaseContext()
    }

    @Singleton
    @Provides
    fun providePokemonOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(NetworkConstants.READ_TIMEOUT.toLong(), NetworkConstants.TIME_UNIT)
            .writeTimeout(NetworkConstants.WRITE_TIMEOUT.toLong(), NetworkConstants.TIME_UNIT)
            .callTimeout(NetworkConstants.CALL_TIMEOUT.toLong(), NetworkConstants.TIME_UNIT)
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun providePokemonRepository(
        pokemonLocalDataSource: PokemonLocalDataSource,
        pokemonRemoteDataSource: PokemonRemoteDataSource
    ): PokemonRepository {
        return PokemonRepositoryImpl(pokemonLocalDataSource, pokemonRemoteDataSource)
    }

    @Provides
    fun providePokemonRemoteDataSource(retrofit: Retrofit): PokemonRemoteDataSource {
        return RetrofitDataSource(retrofit)
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