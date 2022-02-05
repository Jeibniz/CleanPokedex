package com.jeibniz.cleanpokedex.framework.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jeibniz.cleanpokedex.framework.data.local.pokemon.PokemonDao
import com.jeibniz.cleanpokedex.framework.data.local.pokemon.PokemonEntity

@Database(entities = arrayOf(PokemonEntity::class), version = 1, exportSchema = false)
abstract class PokedexDatabase : RoomDatabase() {

    abstract fun getPokemonDao(): PokemonDao

    companion object {
        val DATABASE_NAME: String = "pokemon_database"
    }
}
