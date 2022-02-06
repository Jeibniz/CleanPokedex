package com.jeibniz.cleanpokedex

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jeibniz.cleanpokedex.ui.pokemondetail.PokemonDetailViewModel
import com.jeibniz.cleanpokedex.ui.pokemondetail.PokemonDetailsScreen
import com.jeibniz.cleanpokedex.ui.pokemonlist.PokemonListEvent
import com.jeibniz.cleanpokedex.ui.pokemonlist.PokemonListScreen
import com.jeibniz.cleanpokedex.ui.pokemonlist.PokemonListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    pokemonListViewModel: PokemonListViewModel,
    pokemonDetailViewModel: PokemonDetailViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = PokedexNavigation.PokedexList.route
    ) {
        composable(PokedexNavigation.PokedexList.route) {
            PokemonListScreen(pokemonListViewModel) { event ->
                when (event) {
                    is PokemonListEvent.NavigateToDetails -> navController.navigate(
                        "${PokedexNavigation.PokemonDetails.route}/${event.itemId}")
                }
            }
        }
        composable(
            "${PokedexNavigation.PokemonDetails.route}/{${POKEMON_NUMBER_KEY}}",
            arguments = listOf(navArgument(POKEMON_NUMBER_KEY) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val pokemonNumber = arguments.getInt(POKEMON_NUMBER_KEY)

            PokemonDetailsScreen(pokemonDetailViewModel, pokemonNumber)
            /*
            DogDetailRoute(dogName, viewModel) {
                navController.navigateUp()
            }
             */
        }
    }
}