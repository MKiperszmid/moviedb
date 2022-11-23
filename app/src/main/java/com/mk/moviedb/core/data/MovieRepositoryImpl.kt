package com.mk.moviedb.core.data

import com.mk.moviedb.core.data.mapper.toDomain
import com.mk.moviedb.core.data.remote.MovieApi
import com.mk.moviedb.core.data.remote.extensions.resultOf
import com.mk.moviedb.core.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val api: MovieApi
) : MovieRepository {
    override suspend fun getUpcomingMovies() = resultOf {
        val results = api.getUpcomingMovies().results
        results.map { it.toDomain() }
    }

    override suspend fun getPopularMovies() = resultOf {
        val results = api.getPopularMovies().results
        results.map { it.toDomain() }
    }
}
