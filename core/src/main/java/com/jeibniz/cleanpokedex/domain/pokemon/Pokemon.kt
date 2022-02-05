package com.jeibniz.cleanpokedex.domain.pokemon

data class Pokemon(
    val name: String,
    val number: Int,
    var description: String,
    val imageUrl: String,
    val types: List<String>,
    val height: Int,
    val weight: Int
)
