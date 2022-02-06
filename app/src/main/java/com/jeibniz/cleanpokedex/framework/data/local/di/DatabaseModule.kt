package com.jeibniz.cleanpokedex.framework.data.local.di

import android.content.Context
import androidx.room.Room
import com.jeibniz.cleanpokedex.framework.data.PokedexDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providePokedexDatabase(@ApplicationContext appContext: Context): PokedexDatabase {
        return Room.databaseBuilder(
            appContext,
            PokedexDatabase::class.java,
            PokedexDatabase.DATABASE_NAME
        ).build()
    }
}
