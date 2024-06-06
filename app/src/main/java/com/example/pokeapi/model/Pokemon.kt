package com.example.pokeapi.model

data class Pokemon(
    val name: String,
    val id: Int,
    val height: Int,
    val weight: Int,
    val types: List<PokemonTypeSlot>
)
