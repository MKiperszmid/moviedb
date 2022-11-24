package com.mk.moviedb.core.domain.repository

import com.mk.moviedb.core.domain.model.FilterType
import com.mk.moviedb.core.domain.model.MovieList
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getAllMovies(filterType: FilterType, isFilteredOnly: Boolean): Flow<MovieList>
}
