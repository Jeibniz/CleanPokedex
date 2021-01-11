package com.jeibniz.cleanpokedex.framework.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Organisation::class], version = 1)
abstract class PokedexDatabase: RoomDatabase() {

    abstract fun getOrganisationDao(): OrganisationDao

    companion object{
        val DATABASE_NAME: String = "responder_database"
    }
}
