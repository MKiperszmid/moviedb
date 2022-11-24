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

    // TODO: Migrate to returning only 1 MovieListItem with each type of movie for better scalability

    override fun getUpcomingMovies(): Flow<List<Movie>> {
        return flow {
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

    override fun getPopularMovies(): Flow<List<Movie>> {
        return flow {
            val localMovies = dao.getMovies().filter { it.type == MovieType.TRENDING }
            emit(localMovies.map { it.toDomain() })
            getPopularMoviesRemotely().onSuccess {
                emit(it)
            }.onFailure {
                println()
            }
        }
    }

    private suspend fun getPopularMoviesRemotely() = resultOf {
        val results = api.getPopularMovies().results
        val movies = results.map { it.toDomain() }
        movies.forEach { dao.insertMovie(it.toEntity(MovieType.TRENDING)) }
        movies
    }

    override fun getMoviesByYear(year: Int): Flow<List<Movie>> {
        return flow {
            val localMovies = dao.getMovies().filter { it.type == MovieType.NINETY_THREE }
            emit(localMovies.map { it.toDomain() })
            getMoviesByYearRemotely(year).onSuccess {
                emit(it)
            }.onFailure {
                println()
            }
        }
    }

    private suspend fun getMoviesByYearRemotely(year: Int) = resultOf {
        val results = api.getMoviesByYear(year).results
        val movies = results.map { it.toDomain() }
        movies.forEach { dao.insertMovie(it.toEntity(MovieType.NINETY_THREE)) }
        movies
    }

    override fun getMoviesByLanguage(language: String): Flow<List<Movie>> {
        return flow {
            val localMovies = dao.getMovies().filter { it.type == MovieType.SPANISH }
            emit(localMovies.map { it.toDomain() })
            getMoviesByLanguageRemotely(language).onSuccess {
                emit(it)
            }.onFailure {
                println()
            }
        }
    }

    private suspend fun getMoviesByLanguageRemotely(language: String) = resultOf {
        val results = api.getMoviesByLanguage(language).results
        val movies = results.map { it.toDomain() }
        movies.forEach { dao.insertMovie(it.toEntity(MovieType.SPANISH)) }
        movies
    }
}
