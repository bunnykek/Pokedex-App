package com.example.pokedexapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pokedexapp.s3view.PokemonInfo.PokemonInfo
import com.example.pokedexapp.s3view.PokemonMenu.PokemonMenu
import com.example.pokedexapp.ui.theme.PokemonAppTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            PokemonAppTheme {
                Surface(modifier = Modifier.padding(androidx.compose.foundation.layout.WindowInsets.navigationBars.asPaddingValues())){
                    val navcontroller = rememberNavController()
                    NavHost(navController = navcontroller, startDestination = NavPokemonMenu) {

                        composable<NavPokemonMenu> {
                            PokemonMenu(onNavigateToInfo = { pokemonId: String ->
                                Log.d("finderHelp", pokemonId.toString())
                                navcontroller.navigate(NavPokemonInfo(pokemonId))
                            })
                        }


                        composable<NavPokemonInfo> {
                            val args = it.toRoute<NavPokemonInfo>()
                            PokemonInfo(args.pokemonId, navcontroller)
                        }
                    }
                }
            }
        }
    }
}

@Serializable
object NavPokemonMenu

@Serializable
data class NavPokemonInfo(
    val pokemonId: String
)