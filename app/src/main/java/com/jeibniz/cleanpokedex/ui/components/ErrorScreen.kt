package com.jeibniz.cleanpokedex.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeibniz.cleanpokedex.ui.pokemonlist.PokemonListView
import com.jeibniz.cleanpokedex.ui.pokemonlist.model.PokemonListEntry

@Composable
fun ErrorScreen(onReload: () -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Something went wrong")
        Button(
            onClick = { onReload() },
            colors = ButtonDefaults.textButtonColors()
        ) {
            Text("Try again")
        }
    }
}


@Preview(name = "ErrorScreen")
@Composable
fun ErrorScreenPreview() {
    ErrorScreen { }
}
