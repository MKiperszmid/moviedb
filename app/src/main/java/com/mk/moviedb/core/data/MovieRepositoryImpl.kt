package com.mk.moviedb.core.data

import com.mk.moviedb.core.data.mapper.toDomain
import com.mk.moviedb.core.data.remote.MovieApi
import com.mk.moviedb.core.domain.model.Movie
import com.mk.moviedb.core.domain.repository.MovieRepository

class MovieRepositoryImpl(
    val api: MovieApi
) : MovieRepository {
    override suspend fun getUpcomingMovies(): Result<List<Movie>> {
        return try {
            val results = api.getUpcomingMovies().results
            Result.success(results.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
