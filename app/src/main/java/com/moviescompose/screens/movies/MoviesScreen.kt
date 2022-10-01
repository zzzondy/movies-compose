package com.moviescompose.screens.movies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.moviescompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    navigateToDetails: () -> Unit
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
            title = {
                Text(text = stringResource(id = R.string.title_movies))
            }
        )
    }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text(text = "Movies")
            Button(onClick = {
                navigateToDetails.invoke()
            }) {
                Text(text = "Navigate to details")
            }
        }
    }
}