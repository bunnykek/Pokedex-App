package com.example.pokedexapp.s3view.PokemonInfo.BottomScaffold.Tabs

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokedexapp.s2viewmodel.PokemonInfoModel

@Composable
fun SpeciesTab(pokemonInfoState: PokemonInfoModel.PokemonInfoState) {
    val cardDataList = listOf(
        CardData(
            title = "Species",
            items = listOf(pokemonInfoState.pokemonSpeciesDetails!!.name.replaceFirstChar { it.uppercase() })
        ),
        CardData(
            title = "Colour",
            items = listOf(pokemonInfoState.pokemonSpeciesDetails.color.name.replaceFirstChar { it.uppercase() })
        ),
        CardData(
            title = "Egg Groups",
            items = pokemonInfoState.pokemonSpeciesDetails.egg_groups.map {
                "• ${it.name.replaceFirstChar { it.uppercase() } }"
            }
        ),
        CardData(
            title = "Shape",
            items = listOf(pokemonInfoState.pokemonSpeciesDetails.shape.name.replaceFirstChar { it.uppercase() })
        ),
    )

    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.height(350.dp).padding(10.dp)) {
        items(cardDataList){
            TabCard(cardData = it)
        }
    }
}