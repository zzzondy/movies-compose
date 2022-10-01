package com.moviescompose.screens.details

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.moviescompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navigateBack: () -> Unit
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = stringResource(id = R.string.title_details))
            },
            navigationIcon = {
                IconButton(onClick = { navigateBack.invoke() }) {
                    Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "Arrow back")
                }
            }
        )
    }
    ) { paddingValues ->
        Text(text = "Details screen", modifier = Modifier.padding(paddingValues))
    }
}