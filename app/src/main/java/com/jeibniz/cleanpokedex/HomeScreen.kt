package com.jeibniz.cleanpokedex

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import com.jeibniz.cleanpokedex.ui.pokemondetail.PokemonDetailsScreen
import com.jeibniz.cleanpokedex.ui.pokemonlist.PokemonListScreen
import com.jeibniz.cleanpokedex.ui.pokemonlist.PokemonListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(pokemonListViewModel: PokemonListViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = PokedexNavigation.PokedexList.route
    ) {

        composable(PokedexNavigation.PokedexList.route) {
            PokemonListScreen(pokemonList = listOf()) {}
            /*
            GalleryRoute(viewModel = viewModel) {
                navController.navigate("${PokedexNavigation.Detail.route}/$it")
            }
             */
        }
        composable(
            "${PokedexNavigation.PokemonDetails.route}/{${POKEMON_NAME_KEY}}",
            arguments = listOf(navArgument(POKEMON_NAME_KEY) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val dogName = arguments.getString(POKEMON_NAME_KEY).orEmpty()
            PokemonDetailsScreen(pokemon = Pokemon(
                name = "test", 1, "Desc", "no", listOf(), 1, 2
            ))
            /*
            DogDetailRoute(dogName, viewModel) {
                navController.navigateUp()
            }
             */
        }
    }
}