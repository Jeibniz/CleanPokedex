package com.jeibniz.cleanpokedex.di

import androidx.lifecycle.ViewModelProvider
import com.jeibniz.cleanpokedex.PokedexViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PokedexViewModelFactoryModule {
    @Singleton
    @Provides
    fun providePokedexViewModelFactory(): ViewModelProvider.Factory {
        return PokedexViewModelFactory()
    }
}