package com.mk.moviedb.core.domain.repository

import com.mk.moviedb.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getUpcomingMovies(): Flow<List<Movie>>
    fun getPopularMovies(): Flow<List<Movie>>
    fun getMoviesByYear(year: Int): Flow<List<Movie>>
    fun getMoviesByLanguage(language: String): Flow<List<Movie>>
}
