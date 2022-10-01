package com.moviescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.moviescompose.screens.details.DetailsScreen
import com.moviescompose.screens.main.MainScreen
import com.moviescompose.screens.tabs.MoviesFlowScreens
import com.moviescompose.ui.theme.AppTheme
import com.moviescompose.ui.theme.DarkColors
import com.moviescompose.ui.theme.LightColors

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                val isDarkModeAvailable = isSystemInDarkTheme()
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = if (isDarkModeAvailable) DarkColors.background else LightColors.background,
                        darkIcons = !isDarkModeAvailable
                    )
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { paddingValues ->
                    NavHost(
                        startDestination = "main",
                        navController = navController,
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable("main") { MainScreen() }
                    }
                }
            }
        }
    }
}