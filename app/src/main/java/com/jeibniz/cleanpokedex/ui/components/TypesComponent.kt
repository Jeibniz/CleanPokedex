package com.jeibniz.cleanpokedex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PokemonTypesRow(typesList: List<String>, spacingWidth: Dp = 10.dp) {
    Row {
        typesList.forEach { type ->
            PokemonType(
                type = type
            )
            Spacer(modifier = Modifier.width(spacingWidth))
        }
    }
}

@Composable
fun PokemonType(type: String, modifier: Modifier = Modifier) {
    val shape = RoundedCornerShape(3.dp)
    val color = typeToColor[type] ?: Color.White

    Column(
        modifier = modifier
            .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .clip(shape)
                .background(color)
        ) {
            Text(
                text = type,
                color = Color.White,
                modifier = Modifier
                    .padding(vertical = 1.dp, horizontal = 8.dp)
            )
        }
    }
}

val typeToColor = mapOf(
    "Fire" to Color(0xFFE17421),
    "Ice" to Color(0xFF80D3CA),
    "Poison" to Color(0xFF8E3091),
    "Flying" to Color(0xFF856FBC),
    "Bug" to Color(0xFF95AA18),
    "Grass" to Color(0xFF66BA42),
    "Water" to Color(0xFF577CDE),
    "Ground" to Color(0xFFDDB954),
    "Rock" to Color(0xFFAA9026),
    "Steel" to Color(0xFFA9A9C5),
    "Dragon" to Color(0xFF592CF2),
    "Psychic" to Color(0xFFFF6599),
    "Fairy" to Color(0xFFDA92DA),
    "Dark" to Color(0xFF5F433B),
    "Ghost" to Color(0xFF5A4686),
    "Fighting" to Color(0xFFB5211E),
    "Electric" to Color(0xFFDFBA32),
    "Normal" to Color(0xFF999860)
)

@Preview(name = "PokemonTypesRow")
@Composable
fun PokemonTypesRowPreview() {
    PokemonTypesRow(listOf("Fire", "Dragon"))
}
