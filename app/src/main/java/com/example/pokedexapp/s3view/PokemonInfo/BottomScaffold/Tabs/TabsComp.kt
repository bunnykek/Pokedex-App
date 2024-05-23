package com.example.pokedexapp.s3view.PokemonInfo.BottomScaffold.Tabs

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.example.pokedexapp.s2viewmodel.PokemonInfoModel
import kotlinx.coroutines.launch

data class TabItem(
    val title: String,
)

@Composable
fun PutTabs(pokemonInfoState: PokemonInfoModel.PokemonInfoState) {
    val tabItems = listOf(
        TabItem(
            title = "Info",
        ),
        TabItem(
            title = "Stats",
        ),
        TabItem(
            title = "Species",
        )
    )
    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage){
        tabItems.forEachIndexed { index, item ->
            Tab(selected = index == pagerState.currentPage,
                text = { Text(text = item.title) },
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
    HorizontalPager(state = pagerState) {
        when {
            it == 0 -> InfoTab(pokemonInfoState = pokemonInfoState)
            it == 1 -> StatsTab(pokemonInfoState = pokemonInfoState)
            it == 2 -> SpeciesTab(pokemonInfoState = pokemonInfoState)
        }
    }
}