package com.example.pokedexapp.s2viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.s1model.repository.remote.RetrofitInstance
import com.example.pokedexapp.s1model.repository.remote.randompokemonlist_dto.PokemonList
import kotlinx.coroutines.launch

class PokemonListViewModel: ViewModel() {
    private val _pokemonListState = mutableStateOf(PokemonListState())
    val pokemonListState: State<PokemonListState> = _pokemonListState

    init {
        fetchPokemonList((1..1000).random())
    }

    fun fetchPokemonList(offset: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPokemonList(offset)
                _pokemonListState.value = _pokemonListState.value.copy(
                    loading = false,
                    pokemonList = response
                )
            } catch (e: Exception) {
                _pokemonListState.value = _pokemonListState.value.copy(
                    loading = false,
                    error = "Error occurred ${e.message}"
                )
            }
        }
    }

    data class PokemonListState(
        val loading: Boolean = true,
        val pokemonList: PokemonList = PokemonList(emptyList()),
        val error: String? = null
    )
}