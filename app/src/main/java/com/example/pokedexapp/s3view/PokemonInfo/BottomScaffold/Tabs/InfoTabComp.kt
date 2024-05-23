package com.example.pokedexapp.s3view.PokemonInfo.BottomScaffold.Tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokedexapp.s2viewmodel.PokemonInfoModel

@Composable
fun InfoTab(pokemonInfoState: PokemonInfoModel.PokemonInfoState) {
    Column(modifier = Modifier.height(350.dp).padding(10.dp)) {
        Text(
            text = pokemonInfoState.pokemonSpeciesDetails!!.flavor_text_entries[1].flavor_text.replace("\n", ""),
            style = MaterialTheme.typography.bodyLarge)
        val cardDataList = listOf(
            CardData(
                title = "Height",
                items = listOf("${pokemonInfoState.pokemonDetails!!.height*10} Cm")
            ),
            CardData(
                title = "Weight",
                items = listOf("${pokemonInfoState.pokemonDetails.weight/10} Kg")
            ),
            CardData(
                title = "Abilities",
                items = pokemonInfoState.pokemonDetails.abilities.map { it ->
                    "• ${it.ability.name.replaceFirstChar { it.uppercase() }}"
                }
            ),
            CardData(
                title = "Types",
                items = pokemonInfoState.pokemonDetails.types.map {
                    "• ${it.type.name.replaceFirstChar { it.uppercase() }}"
                }
            ),
        )
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(10.dp)) {
            items(cardDataList){
                TabCard(cardData = it)
            }
        }
    }
}