package com.moviescompose.screens.main

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.moviescompose.screens.settings.SettingsScreen
import com.moviescompose.screens.tabs.MoviesFlowScreens
import com.moviescompose.screens.tabs.moviesFlow
import com.moviescompose.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {
    val childNavController = rememberAnimatedNavController()
    Scaffold(bottomBar = {
        AnimatedBottomNavigationBar(navController = childNavController)
    }) { paddingValues ->
        AnimatedNavHost(
            navController = childNavController,
            startDestination = MainBottomScreen.Movies.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            moviesFlow(navController = childNavController)
            composable(MainBottomScreen.Settings.route) { SettingsScreen() }
        }
    }
}

@Composable
private fun AnimatedBottomNavigationBar(navController: NavController) {
    val screens = listOf(
        MainBottomScreen.Movies,
        MainBottomScreen.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    if (currentDestination?.route == null) return
    val visible =
        screens.any { it.route == currentDestination.route } || currentDestination.route == MoviesFlowScreens.Movies.route
    var enterAnimation = slideInHorizontally(
        animationSpec = tween(700),
        initialOffsetX = { 0 - it })
    var exitAnimation = slideOutHorizontally(
        animationSpec = tween(700),
        targetOffsetX = { 0 - it })
    LaunchedEffect(key1 = Unit) {
        enterAnimation = fadeIn()
        exitAnimation = fadeOut()
    }
    Log.d("TAG", "${currentDestination.route}")
    AnimatedVisibility(
        visible = visible,
        enter = enterAnimation,
        exit = exitAnimation
    ) {
        NavigationBar {
            screens.forEach { screen ->
                val isSelected =
                    currentDestination.hierarchy.any { it.route == screen.route }
                NavigationBarItem(
                    selected = isSelected,
                    icon = {
                        Icon(
                            imageVector = when (screen) {
                                MainBottomScreen.Movies -> Icons.Rounded.List
                                else -> Icons.Rounded.List
                            },
                            contentDescription = stringResource(screen.title)
                        )
                    },
                    label = {
                        Text(text = stringResource(screen.title))
                    },
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomNavigationBar(navController: NavController) {
    val screens = listOf(
        MainBottomScreen.Movies,
        MainBottomScreen.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar {
        screens.forEach { screen ->
            val isSelected =
                currentDestination?.hierarchy?.any { it.route == screen.route } == true
            NavigationBarItem(
                selected = isSelected,
                icon = {
                    Icon(
                        imageVector = when (screen) {
                            MainBottomScreen.Movies -> Icons.Rounded.List
                            else -> Icons.Rounded.List
                        },
                        contentDescription = stringResource(screen.title)
                    )
                },
                label = {
                    Text(text = stringResource(screen.title))
                },
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}


@Preview
@Composable
fun MainScreenPreview() {
    AppTheme {
        MainScreen()
    }
}