package com.jeibniz.cleanpokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jeibniz.cleanpokedex.ui.pokemonlist.PokemonListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PokemonListFragment.newInstance())
                .commitNow()
        }
    }
}