package com.example.pokedexapp.s3view.PokemonInfo

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pokedexapp.s2viewmodel.PokemonInfoModel
import com.example.pokedexapp.s3view.PokemonInfo.BottomScaffold.BottomScaffoldComp


@Composable
fun PokemonInfo(pokemonId: String = "3", navigationController: NavController) {
    Log.d("finderHelp", pokemonId.toString())
    val pokemonInfoModel: PokemonInfoModel = viewModel()
    val pokemonInfoState by pokemonInfoModel.pokemonInfoState
    pokemonInfoModel.fetchPokemonInfo(pokemonId)

    when {
        pokemonInfoState.loading -> {

        }

        pokemonInfoState.error != null -> {
            Text(text = pokemonInfoState.error.toString())
        }

        else -> {
            BottomScaffoldComp(pokemonInfoState = pokemonInfoState, navigationController = navigationController)
        }
    }
}

@Composable
fun Topbar(navigationController: NavController) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, start = 20.dp, end = 20.dp)
    ) {
        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "Back", modifier = Modifier.clickable {
                navigationController.popBackStack()
        })
    }
}









