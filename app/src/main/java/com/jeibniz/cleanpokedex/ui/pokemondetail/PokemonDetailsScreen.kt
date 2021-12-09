package com.jeibniz.cleanpokedex.ui.pokemondetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import com.jeibniz.cleanpokedex.R

@Composable
fun PokemonDetailsScreen(pokemon: Pokemon?) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
        .wrapContentWidth(Alignment.CenterHorizontally)
    ) {
        val spacerHeight = 10.dp

        pokemonImage(pokemon)
        headerText(pokemon)

        Spacer(modifier = Modifier.height(spacerHeight))

        Text(text = pokemon?.description ?: "",
            fontSize = 16.sp)

        Spacer(modifier = Modifier.height(50.dp))

        heightWidthText(pokemon)
    }
}

@Composable
private fun heightWidthText(pokemon: Pokemon?, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        val textSize = 18.sp
        val smallPadding = 10.dp
        val largePadding = 30.dp
        Text(
            text = stringResource(id = R.string.pokemon_height),
            fontSize = textSize,
            modifier = Modifier.padding(end = smallPadding)
        )
        Text(
            text = pokemon?.height.toString(),
            fontSize = textSize,
            modifier = Modifier.padding(end = largePadding)
        )
        Text(
            text = stringResource(id = R.string.pokemon_weight),
            fontSize = textSize,
            modifier = Modifier.padding(end = smallPadding)
        )
        Text(
            text = pokemon?.weight.toString(),
            fontSize = textSize
        )
    }
}

@Composable
private fun pokemonImage(pokemon: Pokemon?, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.padding(horizontal = 60.dp)
    ) {
        Image(
            painter = rememberImagePainter(pokemon?.imageUrl),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()

        )
    }
}

@Composable
private fun headerText(pokemon: Pokemon?, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()

    ) {
        Text(
            text = pokemon?.name ?: "Test",
            fontSize = 24.sp
        )
        Text(
            text = pokemon?.number.toString(),
            fontSize = 18.sp
        )
    }
}

@Preview(name = "Pokemon details")
@Composable
fun PokemonDetailsScreenPreview() {
    PokemonDetailsScreen(
        Pokemon(
        "Ponyta", 45, "Likes it warm",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/77.png",
            listOf("Fire", "Rock"), 1, 2)
    )
}
