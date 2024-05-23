package com.example.pokedexapp.s3view.PokemonMenu

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.pokedexapp.s2viewmodel.PokemonListViewModel
import com.example.pokemonapp.R
import dev.materii.pullrefresh.PullRefreshLayout
import dev.materii.pullrefresh.rememberPullRefreshState

@Composable
fun PokemonMenu(onNavigateToInfo: (String) -> Unit) {
    val pokemonListViewModel: PokemonListViewModel = viewModel()
    val pokemonListViewState by pokemonListViewModel.pokemonListState
    var titleState by remember {
        mutableIntStateOf(0)
    }
    when {
        pokemonListViewState.loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        pokemonListViewState.error != null -> {
            Text(text = pokemonListViewState.error.toString())
        }

        else -> {
            Column {
                AnimatedVisibility(visible = titleState == 0) {
                    MenuTitleBar { titleState = 1 }
                }

                AnimatedVisibility(visible = titleState == 1) {
                    MenuSearchBar({ titleState = 0 }, onNavigateToInfo)
                }

                var isRefreshing by remember {
                    mutableStateOf(false)
                }

                var pullRefreshState = rememberPullRefreshState(
                    refreshing = isRefreshing,
                    onRefresh = {
                        pokemonListViewModel.fetchPokemonList((1..1000).random())
                    }
                )

                PullRefreshLayout(
                    state = pullRefreshState
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 10.dp)
                    ) {
                        LazyVerticalGrid(columns = GridCells.Fixed(count = 2)) {
                            items(pokemonListViewState.pokemonList.results.size) {
                                Thumbnail(pokemonListViewState, it, onNavigateToInfo)
                            }
                        }
                        ElevatedButton(
                            content = {
                                Row {
                                    Icon(imageVector = Icons.Rounded.Refresh, contentDescription = "Refresh", Modifier.padding(top = 4.dp, end = 2.dp))
                                    Text(
                                        text = "Refresh",
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                }
                            },
                            onClick = { pokemonListViewModel.fetchPokemonList((1..1000).random()) },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(bottom = 60.dp, end = 30.dp),
                            colors = ButtonDefaults.elevatedButtonColors(),

                            )
                    }
                }
            }
        }
    }
}

@Composable
fun MenuTitleBar(clicked: () -> Unit) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Pokédex",
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.clickable {
                Toast.makeText(
                    context,
                    "Pokédex by @bunnykek",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
        Icon(
            imageVector = Icons.Rounded.Search,
            contentDescription = "Search",
            modifier = Modifier
                .padding(top = 20.dp)
                .clickable { clicked() })
    }
}

@Composable
fun MenuSearchBar(clicked: () -> Unit, onNavigateToInfo: (String) -> Unit) {
    var typedText by remember {
        mutableStateOf("")
    }
    TextField(value = typedText, onValueChange = {typedText = it}, modifier = Modifier
        .fillMaxWidth()
        .padding(WindowInsets.statusBars.asPaddingValues())
        .padding(horizontal = 10.dp),
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
//                        disabledIndicatorColor = Color.Transparent
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "Search",
                modifier = Modifier.clickable { clicked() })
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "Search",
                modifier = Modifier.clickable { onNavigateToInfo(typedText.trim().lowercase()) })
        },
        placeholder = { Text(text = "pikachu") },
        maxLines = 1
    )
}

@Composable
fun Thumbnail(
    pokemonListViewState: PokemonListViewModel.PokemonListState,
    index: Int,
    onNavigateToInfo: (String) -> Unit
) {
    val parts = pokemonListViewState.pokemonList.results[index].url.split("/")
    val pokemonid = parts[parts.size - 2]
    ElevatedCard(
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                onNavigateToInfo(pokemonid)
            },
    ) {
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/${pokemonid}.png",
                contentDescription = "Pokemon name",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                placeholder = painterResource(id = R.drawable.indeterminate_question_box)
            )
        }
        Text(
            text = pokemonListViewState.pokemonList.results[index].name.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(5.dp)
        )
    }
}
