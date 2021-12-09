package com.jeibniz.cleanpokedex.ui.pokemondetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon

@Composable
fun PokemonDetailsScreen(pokemon: Pokemon?) {
    Column(modifier = Modifier
        .wrapContentWidth(Alignment.CenterHorizontally)) {
        Image(painter = rememberImagePainter(pokemon?.imageUrl), contentDescription = null)
    }
}

@Preview(name = "Pokemon details")
@Composable
fun PokemonDetailsScreenPreview() {
    PokemonDetailsScreen(
        Pokemon(
        "Ponyta", 45, "Likes it warm", "??",
            listOf("Fire", "Rock"), 1, 2)
    )
}
