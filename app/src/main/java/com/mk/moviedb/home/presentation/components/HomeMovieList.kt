package com.mk.moviedb.home.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mk.moviedb.core.domain.model.Movie

@Composable
fun HomeMovieList(
    title: String,
    movies: List<Movie>,
    modifier: Modifier = Modifier,
    onMovieClick: (Movie) -> Unit
) {
    Column(modifier = modifier) {
        CategoryTitle(title)
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(movies) {
                HomeMoviePoster(it.poster, MoviePosterSize.SMALL) {
                    onMovieClick(it)
                }
            }
        }
    }
}
