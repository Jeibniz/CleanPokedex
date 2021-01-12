package com.jeibniz.cleanpokedex.framework.data.local.pokemon

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon_table WHERE :from < number AND number < :to")
    fun getRange(from: Int, to: Int): Flow<List<PokemonEntity>>

    @Query("SELECT * FROM pokemon_table WHERE number = :number")
    fun getByNumber(number: Int): Flow<List<PokemonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: PokemonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(pokemon: List<PokemonEntity>)

    @Query("DELETE FROM pokemon_table")
    suspend fun deleteAll()
}