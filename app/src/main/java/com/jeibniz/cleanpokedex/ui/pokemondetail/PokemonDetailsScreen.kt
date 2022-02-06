package com.jeibniz.cleanpokedex.ui.pokemondetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.jeibniz.cleanpokedex.R
import com.jeibniz.cleanpokedex.data.ErrorResult
import com.jeibniz.cleanpokedex.data.LoadingResult
import com.jeibniz.cleanpokedex.data.Result
import com.jeibniz.cleanpokedex.data.SuccessResult
import com.jeibniz.cleanpokedex.domain.pokemon.Pokemon
import com.jeibniz.cleanpokedex.ui.components.ErrorScreen
import com.jeibniz.cleanpokedex.ui.components.LoadingScreen
import com.jeibniz.cleanpokedex.ui.components.PokemonTypesRow
import com.jeibniz.cleanpokedex.ui.theme.Neutral
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun PokemonDetailsScreen(
    viewModel: PokemonDetailViewModel,
    pokemonNumber: Int,
    onUpPress: () -> Unit
) {
    viewModel.requestPokemon(pokemonNumber)
    val pokemonResult by viewModel.pokemonDetails.collectAsState()
    PokemonDetailsScreen(pokemonResult, onUpPress) {
        viewModel.requestPokemon(pokemonNumber)
    }
}

@ExperimentalCoroutinesApi
@Composable
fun PokemonDetailsScreen(
    pokemonResult: Result<Pokemon>,
    onUpPress: () -> Unit,
    onRefresh: () -> Unit
) {

    when (pokemonResult) {
        is SuccessResult -> PokemonDetailsView(pokemonResult.data, onUpPress)
        is LoadingResult -> LoadingScreen()
        is ErrorResult -> ErrorScreen {
            onRefresh()
        }
    }
}

@ExperimentalCoroutinesApi
@Composable
fun PokemonDetailsView(pokemon: Pokemon, onUpPress: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
            .padding(end = 25.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    ) {
        PokemonHeader(pokemon, onUpPress)
        HeaderText(pokemon)
        Spacer(modifier = Modifier.height(10.dp))
        PokemonTypesRow(pokemon.types)
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = pokemon.description,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(50.dp))
        HeightWidthText(pokemon)
    }
}

@Composable
fun PokemonHeader(pokemon: Pokemon, onUpPress: () -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
    ) {
        PokemonImage(pokemon)
        UpButton(onUpPress)
    }
}

@Composable
private fun UpButton(upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier = Modifier
            .padding(vertical = 10.dp)
            .size(36.dp)
            .background(
                color = Neutral.copy(alpha = 0.32f),
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            tint = Color.White,
            contentDescription = "Back"
        )
    }
}

@Composable
private fun PokemonImage(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.padding(horizontal = 60.dp)
    ) {
        Image(
            painter = rememberImagePainter(pokemon.imageUrl),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun HeaderText(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = pokemon.name,
            fontSize = 24.sp
        )
        Text(
            text = pokemon.number.toString().padStart(3, '0'),
            fontSize = 18.sp
        )
    }
}

@Composable
private fun HeightWidthText(pokemon: Pokemon, modifier: Modifier = Modifier) {
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
            text = pokemon.height.toString(),
            fontSize = textSize,
            modifier = Modifier.padding(end = largePadding)
        )
        Text(
            text = stringResource(id = R.string.pokemon_weight),
            fontSize = textSize,
            modifier = Modifier.padding(end = smallPadding)
        )
        Text(
            text = pokemon.weight.toString(),
            fontSize = textSize
        )
    }
}

@Preview(name = "Pokemon details")
@ExperimentalCoroutinesApi
@Composable
fun PokemonDetailsScreenPreview() {
    PokemonDetailsView(
        Pokemon(
            "Ponyta", 45, "Likes it warm",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/77.png",
            listOf("Fire", "Rock"), 1, 2
        )
    ) {}
}
