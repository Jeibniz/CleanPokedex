package com.jeibniz.cleanpokedex.framework.data.remote.di

import com.jeibniz.cleanpokedex.framework.data.remote.pokemon.DetailedPokemonApi
import com.jeibniz.cleanpokedex.framework.data.remote.pokemon.GeneralPokemonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    fun provideDetailedPokemonApi(
        retrofit: Retrofit
    ): DetailedPokemonApi {
        return retrofit.create(DetailedPokemonApi::class.java)
    }

    @Provides
    fun provideGeneralPokemonApi(
        retrofit: Retrofit
    ): GeneralPokemonApi {
        return retrofit.create(GeneralPokemonApi::class.java)
    }
}

