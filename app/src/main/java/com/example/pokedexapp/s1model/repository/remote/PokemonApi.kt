package com.example.pokedexapp.s1model.repository.remote

import com.example.pokedexapp.s1model.repository.remote.pokemondetails_dto.PokemonDetails
import com.example.pokedexapp.s1model.repository.remote.pokemonspecies_dto.PokemonSpeciesDetails
import com.example.pokedexapp.s1model.repository.remote.randompokemonlist_dto.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {
    @GET("pokemon")
    suspend fun getPokemonList(@Query("offset") offset: Int): PokemonList

    @GET("pokemon/{pokemonId}")
    suspend fun getPokemonDetails(@Path("pokemonId") pokemonId: String): PokemonDetails

    @GET("pokemon-species/{pokemonId}")
    suspend fun getPokemonSpeciesDetails(@Path("pokemonId") pokemonId: String): PokemonSpeciesDetails
}