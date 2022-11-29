package com.mk.moviedb.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mk.moviedb.R
import com.mk.moviedb.core.domain.model.Movie
import com.mk.moviedb.home.presentation.components.*

const val COLUMS_IN_GRID = 2

@Composable
fun HomeScreen(
    onMovieClick: (Movie) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize().padding(start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(span = {
            GridItemSpan(COLUMS_IN_GRID)
        }) {
            HomeHeader()
        }

        if (state.upcomingMovies.isNotEmpty()) {
            item(span = {
                GridItemSpan(COLUMS_IN_GRID)
            }) {
                HomeMovieList(
                    stringResource(R.string.upcoming_releases),
                    movies = state.upcomingMovies
                ) {
                    onMovieClick(it)
                }
            }
        }

        if (state.popularMovies.isNotEmpty()) {
            item(span = {
                GridItemSpan(COLUMS_IN_GRID)
            }) {
                HomeMovieList(
                    stringResource(R.string.popular_movies),
                    movies = state.popularMovies
                ) {
                    onMovieClick(it)
                }
            }
        }
        if (state.filteredMovies.isNotEmpty()) {
            item(span = {
                GridItemSpan(COLUMS_IN_GRID)
            }) {
                HomeRecommended(selectedFilter = state.selectedFilter, onFilterClick = {
                    viewModel.onEvent(HomeEvent.ChangeFilter(it))
                })
            }
        }

        items(state.filteredMovies) {
            HomeMoviePoster(it.poster, MoviePosterSize.BIG) {
                onMovieClick(it)
            }
        }
    }
    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}
