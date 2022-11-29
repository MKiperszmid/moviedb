package com.mk.moviedb.core.domain.repository

import com.mk.moviedb.core.domain.model.FilterType
import com.mk.moviedb.core.domain.model.Movie
import com.mk.moviedb.core.domain.model.MovieDetail
import com.mk.moviedb.core.domain.model.MovieList
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getAllMovies(filterType: FilterType, isFilteredOnly: Boolean): Flow<MovieList>
    suspend fun getMovieById(id: Int): Result<MovieDetail>
}
