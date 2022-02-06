package com.jeibniz.cleanpokedex

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.jeibniz.cleanpokedex.ui.pokemondetail.PokemonDetailViewModel
import com.jeibniz.cleanpokedex.ui.pokemonlist.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val pokemonListViewModel by viewModels<PokemonListViewModel>()
    private val pokemonDetailViewModel by viewModels<PokemonDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colors.background) {
                HomeScreen(pokemonListViewModel, pokemonDetailViewModel)
            }
        }
    }
}
