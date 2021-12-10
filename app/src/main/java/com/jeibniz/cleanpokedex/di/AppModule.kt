package com.jeibniz.cleanpokedex.di

import android.content.Context
import androidx.room.Room
import com.jeibniz.cleanpokedex.BaseApplication
import com.jeibniz.cleanpokedex.data.pokemon.PokemonLocalDataSource
import com.jeibniz.cleanpokedex.data.pokemon.PokemonRemoteDataSource
import com.jeibniz.cleanpokedex.data.pokemon.PokemonRepository
import com.jeibniz.cleanpokedex.data.pokemon.PokemonRepositoryImpl
import com.jeibniz.cleanpokedex.framework.data.PokedexDatabase
import com.jeibniz.cleanpokedex.framework.data.local.pokemon.PokemonDao
import com.jeibniz.cleanpokedex.framework.data.local.pokemon.RoomDataSource
import com.jeibniz.cleanpokedex.framework.data.pokemon.remote.NetworkConstants
import com.jeibniz.cleanpokedex.framework.data.remote.pokemon.RetrofitDataSource
import com.jeibniz.cleanpokedex.usecases.pokemondetail.GetPokemon
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

    // Room

    @Singleton
    @Provides
    fun providePokedexDatabase(context: Context): PokedexDatabase {
        return Room.databaseBuilder(
                context.applicationContext,
                PokedexDatabase::class.java,
                PokedexDatabase.DATABASE_NAME
            ).build()
    }

    @Provides
    fun providePokemonDao(pokedexDatabase: PokedexDatabase): PokemonDao {
        return pokedexDatabase.getPokemonDao()
    }

    // Retrofit

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

    // Repository

    @Provides
    fun providePokemonRepository(
        pokemonLocalDataSource: PokemonLocalDataSource,
        pokemonRemoteDataSource: PokemonRemoteDataSource
    ): PokemonRepository {
        return PokemonRepositoryImpl(pokemonLocalDataSource, pokemonRemoteDataSource)
    }

    // Data sources

    @Provides
    fun providePokemonRemoteDataSource(retrofit: Retrofit): PokemonRemoteDataSource {
        return RetrofitDataSource(retrofit)
    }

    @Provides
    fun providePokemonLocalDataSource(pokemonDao: PokemonDao): PokemonLocalDataSource {
        return RoomDataSource(pokemonDao)
    }

    // Use cases

    @Provides
    fun provideGetGenOnePokemons(pokemonRepository: PokemonRepository): GetGenOnePokemons {
        return GetGenOnePokemons(pokemonRepository)
    }

    @Provides
    fun provideGetPokemon(pokemonRepository: PokemonRepository): GetPokemon {
        return GetPokemon(pokemonRepository)
    }
}