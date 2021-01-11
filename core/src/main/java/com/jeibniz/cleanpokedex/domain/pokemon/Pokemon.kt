package com.jeibniz.cleanpokedex.domain.pokemon

data class Pokemon(
    val name: String,
    val number: Int,
    val description: String,
    val imageUrl: String,
    val types: List<String>,
    val height: Int,
    val weight: Int
    )
