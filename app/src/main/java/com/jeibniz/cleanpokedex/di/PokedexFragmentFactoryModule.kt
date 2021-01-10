package com.jeibniz.cleanpokedex.di

import android.graphics.Typeface
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.jeibniz.cleanpokedex.PokedexFragmentFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PokedexFragmentFactoryModule {
    @Singleton
    @Provides
    fun provideResponderFragmentFactory(
        viewModelFactory: ViewModelProvider.Factory
    ): FragmentFactory {
        return PokedexFragmentFactory(viewModelFactory)
    }
}