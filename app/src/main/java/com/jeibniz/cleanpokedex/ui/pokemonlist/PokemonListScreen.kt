package com.jeibniz.cleanpokedex.ui.pokemonlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.jeibniz.cleanpokedex.data.ErrorResult
import com.jeibniz.cleanpokedex.data.LoadingResult
import com.jeibniz.cleanpokedex.data.Result
import com.jeibniz.cleanpokedex.data.SuccessResult
import com.jeibniz.cleanpokedex.ui.components.ErrorScreen
import com.jeibniz.cleanpokedex.ui.components.LoadingScreen
import com.jeibniz.cleanpokedex.ui.components.PokemonTypesRow
import com.jeibniz.cleanpokedex.ui.pokemonlist.model.PokemonListEntry

sealed class PokemonListEvent {
    data class NavigateToDetails(val itemId: Int) : PokemonListEvent()
}

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel,
    onEvent: (PokemonListEvent) -> Unit
) {
    val pokemonList by viewModel.pokemonList.collectAsState()
    PokemonListScreen(pokemonList, onEvent) { viewModel.requestPokemons() }
}

@Composable
fun PokemonListScreen(
    pokemonList: Result<List<PokemonListEntry>>,
    onEvent: (PokemonListEvent) -> Unit,
    onRefresh: () -> Unit
) {
    when (pokemonList) {
        is SuccessResult -> PokemonListView(pokemonList.data, onEvent)
        is LoadingResult -> LoadingScreen()
        is ErrorResult -> ErrorScreen {
            onRefresh()
        }
    }
}

@Composable
fun PokemonListView(
    pokemonList: List<PokemonListEntry>,
    onEvent: (PokemonListEvent) -> Unit
) {
    LazyColumn() {
        items(pokemonList) { pokemon ->
            PokemonRow(
                pokemon = pokemon,
                modifier = Modifier
                    .clickable(onClick = { onItemClick(pokemon, onEvent) })
            )
            Divider()
        }
    }
}

fun onItemClick(pokemon: PokemonListEntry, onEvent: (PokemonListEvent) -> Unit) {
    val event = PokemonListEvent.NavigateToDetails(pokemon.number)
    onEvent(event)
}

@Composable
fun PokemonRow(pokemon: PokemonListEntry, modifier: Modifier = Modifier) {
    val viewHeight = 120.dp

    Row(modifier = modifier) {
        PokemonImage(pokemon, viewHeight)
        PokemonInfo(viewHeight, pokemon)
    }
}

@Composable
private fun PokemonInfo(
    viewHeight: Dp,
    pokemon: PokemonListEntry
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .height(viewHeight)
            .padding(top = 10.dp, bottom = 20.dp)
    ) {
        PokemonDetailsInfo(pokemon)
        PokemonTypesRow(pokemon.types)
    }
}

@Composable
private fun PokemonDetailsInfo(pokemon: PokemonListEntry) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 20.dp)
    ) {
        Text(
            text = pokemon.name,
            fontSize = 22.sp
        )
        Text(
            text = pokemon.number.toString().padStart(3, '0'),
            fontSize = 18.sp
        )
    }
}

@Composable
private fun PokemonImage(
    pokemon: PokemonListEntry,
    viewHeight: Dp
) {
    Image(
        painter = rememberImagePainter(pokemon.imageUrl),
        contentDescription = null,
        modifier = Modifier
            .width(viewHeight)
            .height(viewHeight)
    )
}


@Preview(name = "Pokemon List")
@Composable
fun PokemonListScreenPreview() {
    val list = listOf(
        PokemonListEntry(
            name = "Geodude",
            number = 74,
            listOf("Rock", "Ground"),
            "\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/74.png\""
        ),
        PokemonListEntry(
            name = "Geodude2",
            number = 75,
            listOf("Rock", "Water"),
            "\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/75.png\""
        ),
        PokemonListEntry(
            name = "Geodude3",
            number = 76,
            listOf("Rock", "Fire"),
            "\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/76.png\""
        ),
    )

    PokemonListView(list) { _ -> }
}
