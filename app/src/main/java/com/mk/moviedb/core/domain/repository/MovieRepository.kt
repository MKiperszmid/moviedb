package com.mk.moviedb.core.domain.repository

import com.mk.moviedb.core.domain.model.Movie

interface MovieRepository {
    suspend fun getUpcomingMovies(): List<Movie>
}
