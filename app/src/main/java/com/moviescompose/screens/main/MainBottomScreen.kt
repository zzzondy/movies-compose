package com.moviescompose.screens.main

import androidx.annotation.StringRes
import com.moviescompose.R

sealed class MainBottomScreen(val route: String, @StringRes val title: Int) {
    object Movies : MainBottomScreen("moviesFlow", R.string.title_movies)
    object Settings : MainBottomScreen("settings", R.string.title_settings)
}
