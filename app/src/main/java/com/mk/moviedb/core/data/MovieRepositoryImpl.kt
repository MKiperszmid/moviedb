package com.mk.moviedb.core.data

import com.mk.moviedb.core.data.local.MovieDao
import com.mk.moviedb.core.data.local.entity.MovieType
import com.mk.moviedb.core.data.mapper.toDomain
import com.mk.moviedb.core.data.mapper.toEntity
import com.mk.moviedb.core.data.remote.MovieApi
import com.mk.moviedb.core.data.remote.extensions.resultOf
import com.mk.moviedb.core.domain.model.Movie
import com.mk.moviedb.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val api: MovieApi,
    private val dao: MovieDao
) : MovieRepository {

    override fun getUpcomingMovies(): Flow<List<Movie>> {
        return flow {
            // TODO: Return only 1 MovieListItem with each type of movie?
            val localMovies = dao.getMovies().filter { it.type == MovieType.UPCOMING }
            emit(localMovies.map { it.toDomain() })
            getUpcomingMoviesRemotely().onSuccess {
                emit(it)
            }.onFailure {
                println()
            }
        }
    }

    private suspend fun getUpcomingMoviesRemotely() = resultOf {
        val results = api.getUpcomingMovies().results
        val movies = results.map { it.toDomain() }
        movies.forEach { dao.insertMovie(it.toEntity(MovieType.UPCOMING)) }
        movies
    }

    override suspend fun getPopularMovies() = resultOf {
        val results = api.getPopularMovies().results
        results.map { it.toDomain() }
    }

    override suspend fun getMoviesByYear(year: Int) = resultOf {
        val results = api.getMoviesByYear(year).results
        results.map { it.toDomain() }
    }

    override suspend fun getMoviesByLanguage(language: String) = resultOf {
        val results = api.getMoviesByLanguage(language).results
        results.map { it.toDomain() }
    }
}
