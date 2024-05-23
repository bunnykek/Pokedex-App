package com.example.pokedexapp.s1model.repository.remote.pokemondetails_dto

data class PokemonDetails(
    val abilities: List<Ability>,
    val base_experience: Int,
    val forms: List<Form>,
    val height: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val species: Species,
    val sprites: Sprites,
    val types: List<Type>,
    val weight: Int
)