package com.example.pokedexapp.s3view.PokemonInfo.BottomScaffold.Tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TabCard(cardData: CardData) {
    Card(modifier = Modifier.padding(5.dp),
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = cardData.title,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            cardData.items.forEach {
                Text(text = it, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

data class CardData(
    val title: String,
    val items: List<String>
)