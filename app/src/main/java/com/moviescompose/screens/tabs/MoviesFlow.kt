package com.moviescompose.screens.tabs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.moviescompose.screens.main.MainBottomScreen
import com.moviescompose.screens.movies.MoviesScreen

fun NavGraphBuilder.moviesFlow(
    navController: NavController
) {
    navigation(startDestination = "movies", route = MainBottomScreen.Movies.route) {
        composable("movies") {
            MoviesScreen(navController = navController)
        }
    }
}