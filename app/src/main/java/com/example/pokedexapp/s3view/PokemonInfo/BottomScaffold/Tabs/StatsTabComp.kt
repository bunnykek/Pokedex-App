package com.example.pokedexapp.s3view.PokemonInfo.BottomScaffold.Tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.pokedexapp.s2viewmodel.PokemonInfoModel

@Composable
fun StatsTab(pokemonInfoState: PokemonInfoModel.PokemonInfoState) {
    Column(modifier = Modifier.height(350.dp)
        .padding(10.dp)
        .fillMaxHeight(.5f)) {
        Row {
            Column {
                Text(text = "BE  ", style = MaterialTheme.typography.titleLarge)
                Text(text = "BH  ", style = MaterialTheme.typography.titleLarge)
                Text(text = "CR  ", style = MaterialTheme.typography.titleLarge)


            }
            Column {
                Box {
                    LinearProgressIndicator(
                        progress = (pokemonInfoState.pokemonDetails!!.base_experience / 300f),
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth()
                            .height(20.dp)
                            .clip(CircleShape),
                        trackColor = MaterialTheme.colorScheme.primaryContainer,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "${pokemonInfoState.pokemonDetails.base_experience}/300",
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(top = 3.dp, end = 3.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Box {
                    LinearProgressIndicator(
                        progress = pokemonInfoState.pokemonSpeciesDetails!!.base_happiness / 255f,
                        modifier = Modifier
                            .padding(top = 9.dp)
                            .fillMaxWidth()
                            .height(20.dp)
                            .clip(
                                RoundedCornerShape(10.dp)
                            ),
                        trackColor = MaterialTheme.colorScheme.tertiaryContainer,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    Text(
                        text = "${pokemonInfoState.pokemonSpeciesDetails.base_happiness}/255",
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(top = 7.dp, end = 3.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Box {
                    LinearProgressIndicator(
                        progress = pokemonInfoState.pokemonSpeciesDetails!!.capture_rate / 255f,
                        modifier = Modifier
                            .padding(top = 9.dp)
                            .fillMaxWidth()
                            .height(20.dp)
                            .clip(
                                RoundedCornerShape(10.dp)
                            ),
                        trackColor = MaterialTheme.colorScheme.secondaryContainer,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "${pokemonInfoState.pokemonSpeciesDetails.capture_rate}/255",
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(top = 6.dp, end = 3.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "BE=Base experience\nBH=Base happiness\nCR=Capture rate",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
        )
    }
}