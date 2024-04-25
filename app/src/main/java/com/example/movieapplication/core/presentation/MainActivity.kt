package com.example.movieapplication.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapplication.MainViewModel
import com.example.movieapplication.presentation.nvgraph.NavGraph
import com.example.movieapplication.presentation.nvgraph.Route
import com.example.movieapplication.ui.theme.MovieApplicationTheme
import com.example.movieapplication.util.Screen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition
            }
        }
        setContent {
            MovieApplicationTheme {
                SetBarColor(color = MaterialTheme.colorScheme.inverseOnSurface)
                val isSystemInDarkMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()

//                SideEffect {
//                    systemController.setSystemBarsColor(
//                        color = Color.Transparent,
//                        darkIcons = !isSystemInDarkMode
//                    )
//                }
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {

                    // A surface container using the 'background' color from the theme
//                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background))
//                {
//                    val startDestination=viewModel.startDestination
//                    NavGraph(startDestination = startDestination)
//                }
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.Home.rout) {
                        composable(Screen.Home.rout) {
                            HomeScreen(navController)
                        }
                        composable(
                            Screen.Details.rout + "/{movieId}",
                            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
                        ) { backBackStackEntry ->
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SetBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(key1 = color) {
        systemUiController.setSystemBarsColor(color)
    }
}
