package com.moviescompose.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.moviescompose.screens.tabs.moviesFlow
import com.moviescompose.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController
) {
    val childNavController = rememberNavController()

    val navigationItems = listOf(MainBottomScreen.Movies)
    Scaffold(bottomBar = {
        NavigationBar {
            val navBackStackEntry by childNavController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val previousDestination = remember { mutableStateOf(navigationItems.first().route) }
            navigationItems.forEach { screen ->
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
                        if (screen.route == previousDestination.value) return@NavigationBarItem
                        previousDestination.value = screen.route

                        childNavController.navigate(screen.route) {
                            popUpTo(childNavController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }) { paddingValues ->
        NavHost(
            navController = childNavController,
            startDestination = MainBottomScreen.Movies.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            moviesFlow(navController)
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    AppTheme {
        MainScreen(navController = rememberNavController())
    }
}