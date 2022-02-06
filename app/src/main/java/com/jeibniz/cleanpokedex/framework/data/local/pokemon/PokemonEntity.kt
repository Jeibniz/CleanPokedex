package com.jeibniz.cleanpokedex.framework.data.local.pokemon

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
data class PokemonEntity(
    @PrimaryKey
    val number: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val types: String,
    val height: Int,
    val weight: Int,
    val storedTime: Long
)
