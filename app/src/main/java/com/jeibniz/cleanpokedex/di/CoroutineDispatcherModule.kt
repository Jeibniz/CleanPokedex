package com.jeibniz.cleanpokedex.di

import com.jeibniz.cleanpokedex.utils.DefaultDispatcherProvider
import com.jeibniz.cleanpokedex.utils.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoroutineDispatcherModule {
    @Binds
    @Singleton
    abstract fun bindDispatcherProvider(provider: DefaultDispatcherProvider): DispatcherProvider
}