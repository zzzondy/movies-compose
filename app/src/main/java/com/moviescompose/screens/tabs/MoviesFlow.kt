package com.moviescompose.screens.tabs

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.moviescompose.R
import com.moviescompose.screens.details.DetailsScreen
import com.moviescompose.screens.main.MainBottomScreen
import com.moviescompose.screens.movies.MoviesScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.moviesFlow(
    navController: NavController
) {
    navigation(
        startDestination = MoviesFlowScreens.Movies.route,
        route = MainBottomScreen.Movies.route
    ) {
        composable(MoviesFlowScreens.Movies.route,
            exitTransition = {
                when (targetState.destination.route) {
                    MoviesFlowScreens.Details.route -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    MoviesFlowScreens.Details.route -> slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                    else -> null
                }
            }
        ) {
            MoviesScreen(navigateToDetails = { navController.navigate(MoviesFlowScreens.Details.route) })
        }
        composable(MoviesFlowScreens.Details.route,
            enterTransition = {
                when (initialState.destination.route) {
                    MoviesFlowScreens.Movies.route ->
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(700)
                        )
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    MoviesFlowScreens.Movies.route ->
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(700)
                        )
                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    MoviesFlowScreens.Movies.route ->
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(700)
                        )
                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    MoviesFlowScreens.Movies.route ->
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(700)
                        )
                    else -> null
                }
            }) {
            DetailsScreen(navigateBack = { navController.popBackStack() })
        }
    }
}

sealed class MoviesFlowScreens(val route: String, @StringRes val title: Int) {
    object Movies : MoviesFlowScreens(route = "movies", R.string.title_movies)
    object Details : MoviesFlowScreens(route = "details", R.string.title_details)
}