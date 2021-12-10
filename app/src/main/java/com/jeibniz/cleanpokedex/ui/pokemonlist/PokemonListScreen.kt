package com.jeibniz.cleanpokedex.ui.pokemonlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.jeibniz.cleanpokedex.ui.pokemonlist.model.PokemonListEntry

sealed class PokemonListEvent {
    data class NavigateToDetails(val itemId: Int) : PokemonListEvent()
}


@Composable
fun PokemonListScreen(pokemonList: List<PokemonListEntry>,
                      onEvent: (PokemonListEvent) -> Unit) {
    LazyColumn() {
        items(pokemonList) { pokemon ->
            PokemonRow(
                pokemon = pokemon,
                modifier = Modifier
                    .clickable(onClick = { onItemClick(pokemon, onEvent) }))
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
    Row(modifier = modifier) {
        Image(
            painter = rememberImagePainter(pokemon.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .width(120.dp)
                .height(120.dp)
        )

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(120.dp)
                .padding(vertical = 10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp)
            ) {
                Text(text = pokemon.name,
                    fontSize = 22.sp)
                Text(text = pokemon.number.toString().padStart(3, '0'),
                    fontSize = 18.sp)
            }

            Row {
                pokemon.types.forEach { type ->
                    Text(
                        text = type,
                        modifier = Modifier.padding(end = 10.dp)
                    )
                }
            }
        }
    }
}