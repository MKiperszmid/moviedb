package com.mk.moviedb.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mk.moviedb.R
import com.mk.moviedb.home.presentation.components.HomeMovieList

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    LazyColumn(modifier = Modifier.fillMaxSize().padding(start = 25.dp)) {
        item {
            Box(
                modifier = Modifier.fillMaxWidth().height(90.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_marca),
                    contentDescription = "eMovie"
                )
                // TODO: The Logo from FIGMA couldn't be exported.
            }
        }

        if (state.upcoming.isNotEmpty()) {
            item {
                HomeMovieList(
                    "Proximos Estrenos",
                    posters = state.upcoming.map { it.poster }
                )
            }
        }
    }
    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}
