package com.example.pokedexapp.s2viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.s1model.repository.remote.RetrofitInstance
import com.example.pokedexapp.s1model.repository.remote.pokemondetails_dto.PokemonDetails
import com.example.pokedexapp.s1model.repository.remote.pokemonspecies_dto.PokemonSpeciesDetails
import kotlinx.coroutines.launch

class PokemonInfoModel: ViewModel() {
    private val _pokemonInfoState = mutableStateOf(PokemonInfoState())
    val pokemonInfoState: State<PokemonInfoState> = _pokemonInfoState

    fun fetchPokemonInfo(pokemonId: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPokemonDetails(pokemonId = pokemonId)
                _pokemonInfoState.value = _pokemonInfoState.value.copy(
                    pokemonDetails = response
                )
                val response2 = RetrofitInstance.api.getPokemonSpeciesDetails(pokemonId = pokemonId)
                _pokemonInfoState.value = _pokemonInfoState.value.copy(
                    loading = false,
                    pokemonSpeciesDetails = response2
                )
            } catch (e: Exception) {
                _pokemonInfoState.value = _pokemonInfoState.value.copy(
                    loading = false,
                    error = "Error occurred ${e.message}"
                )
            }
        }
    }


    data class PokemonInfoState(
        val loading: Boolean = true,
        val pokemonDetails: PokemonDetails? = null,
        val pokemonSpeciesDetails: PokemonSpeciesDetails? = null,
        val error: String? = null
    )
}