package com.example.pokedexapp.s1model.repository.remote.pokemondetails_dto

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)