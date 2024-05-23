package com.example.pokedexapp.s3view.PokemonInfo.BottomScaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.pillStar
import androidx.graphics.shapes.star
import androidx.graphics.shapes.toPath
import androidx.navigation.NavController
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pokedexapp.s2viewmodel.PokemonInfoModel
import com.example.pokedexapp.s3view.PokemonInfo.BottomScaffold.Tabs.PutTabs
import com.example.pokedexapp.s3view.PokemonInfo.Topbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomScaffoldComp(pokemonInfoState: PokemonInfoModel.PokemonInfoState, navigationController: NavController){
    BottomSheetScaffold(
        modifier = Modifier.fillMaxWidth(),
        sheetContent = {
            PutTabs(pokemonInfoState)
        },
        sheetPeekHeight = 200.dp,
//        sheetTonalElevation = 3.dp,
        sheetShadowElevation = 5.dp,
        topBar = { Topbar(navigationController) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Spacer(modifier = Modifier.height(100.dp))

            var colorPalette: Palette by remember {
                mutableStateOf(
                    Palette.from(listOf(Palette.Swatch(Color.Gray.toArgb(), 0)))
                )
            }
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).allowHardware(false)
                    .data(pokemonInfoState.pokemonDetails!!.sprites.other.home.front_default)
                    .build(),
                onSuccess = {
                    val drawable = it.result.drawable.toBitmap()
                    colorPalette = Palette.from(drawable).generate()
                },
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.CenterHorizontally)
                    .drawBehind {
                        drawPath(
                            listOf(
                                RoundedPolygon.star(
                                    numVerticesPerRadius = (6..15).random(),
                                    centerX = size.width / 2,
                                    centerY = size.height / 3,
                                    innerRadius = 130.dp.toPx(),
                                    radius = 150.dp.toPx(),
                                    rounding = CornerRounding(radius = 50.dp.toPx())
                                ),
                                RoundedPolygon(
                                    numVertices = (3..12).random(),
                                    centerX = size.width / 2,
                                    centerY = size.height / 3,
                                    radius = 150.dp.toPx(),
                                    rounding = CornerRounding(radius = 50.dp.toPx())
                                )
                            )
                                .random()
                                .toPath()
                                .asComposePath(),
                            color = Color(colorPalette.getVibrantColor(Color.Gray.toArgb())).copy(
                                alpha = 0.5f
                            )
                        )
                    }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "#${pokemonInfoState.pokemonDetails.id}",
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .drawBehind {
                        drawPath(
                            RoundedPolygon
                                .pillStar(
                                    numVerticesPerRadius = 9,
                                    height = 20.dp.toPx(),
                                    width = 50.dp.toPx(),
                                    centerX = size.width / 2,
                                    centerY = size.height / 2,
                                    innerRounding = CornerRounding(10.dp.toPx()),
                                    rounding = CornerRounding(10.dp.toPx())
                                )
                                .toPath()
                                .asComposePath(),
                            color = Color(colorPalette.getMutedColor(Color.Gray.toArgb())).copy(
                                alpha = 0.5f
                            )
                        )
                    })
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = pokemonInfoState.pokemonDetails.name.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

}