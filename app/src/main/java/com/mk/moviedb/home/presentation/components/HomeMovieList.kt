package com.mk.moviedb.home.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeMovieList(
    title: String,
    posters: List<String>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        CategoryTitle(title)
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(posters) {
                HomeMoviePoster(it, MoviePosterSize.SMALL)
            }
        }
    }
}
