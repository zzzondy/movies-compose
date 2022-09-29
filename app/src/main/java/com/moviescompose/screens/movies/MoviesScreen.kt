package com.moviescompose.screens.movies

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.moviescompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    navController: NavController,
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
            title = {
                Text(text = stringResource(id = R.string.title_movies))
            }
        )
    }) { paddingValues ->
        Text(text = "Movies screen", modifier = Modifier.padding(paddingValues))
    }
}