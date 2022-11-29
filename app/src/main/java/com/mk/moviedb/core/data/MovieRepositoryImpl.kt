package com.mk.moviedb.core.data

import com.mk.moviedb.core.data.local.MovieDao
import com.mk.moviedb.core.data.local.entity.MovieEntity
import com.mk.moviedb.core.data.local.entity.MovieType
import com.mk.moviedb.core.data.mapper.toDomain
import com.mk.moviedb.core.data.mapper.toEntity
import com.mk.moviedb.core.data.remote.MovieApi
import com.mk.moviedb.core.data.remote.extensions.resultOf
import com.mk.moviedb.core.domain.model.FilterType
import com.mk.moviedb.core.domain.model.Movie
import com.mk.moviedb.core.domain.model.MovieDetail
import com.mk.moviedb.core.domain.model.MovieList
import com.mk.moviedb.core.domain.repository.MovieRepository
import com.mk.moviedb.core.domain.usecase.ReduceFilteredMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val api: MovieApi,
    private val dao: MovieDao,
    private val reduceFilteredMovies: ReduceFilteredMovies
) : MovieRepository {

    // TODO: We could have 1 method for movies, and 1 for filtered movies
    override fun getAllMovies(filterType: FilterType, isFilteredOnly: Boolean): Flow<MovieList> {
        return flow {
            emit(getMovieListLocally(filterType))

            if (!isFilteredOnly) {
                getUpcomingMoviesRemotely().onSuccess {
                    saveMoviesLocally(it, MovieType.UPCOMING)
                    emit(getMovieListLocally(filterType))
                }.onFailure {
                    println()
                }

                getPopularMoviesRemotely().onSuccess {
                    saveMoviesLocally(it, MovieType.TRENDING)
                    emit(getMovieListLocally(filterType))
                }.onFailure {
                    println()
                }
            }

            // TODO: Use SealedClass or similar to make it dynamic so we don't have to hardcode "es" and 1993
            getMoviesByLanguageRemotely("es").onSuccess {
                saveMoviesLocally(it, MovieType.SPANISH)
                emit(getMovieListLocally(filterType))
            }.onFailure {
                println()
            }

            getMoviesByYearRemotely(1997).onSuccess {
                saveMoviesLocally(it, MovieType.NINETY_THREE)
                emit(getMovieListLocally(filterType))
            }.onFailure {
                println()
            }
        }
    }

    override suspend fun getMovieById(id: Int): Result<MovieDetail> = resultOf {
        api.getMovieById(id).toDomain()
    }

    private suspend fun saveMoviesLocally(movies: List<Movie>, movieType: MovieType) {
        movies.forEach { dao.insertMovie(it.toEntity(movieType)) }
    }

    private suspend fun getMovieListLocally(filterType: FilterType): MovieList {
        val localMovies = dao.getMovies()
        val movieTypeFromFilter = when (filterType) {
            FilterType.SPANISH -> MovieType.SPANISH
            FilterType.NINETY_THREE -> MovieType.NINETY_THREE
        }

        return MovieList(
            upcoming = filterAndMapMovies(localMovies, MovieType.UPCOMING),
            trending = filterAndMapMovies(localMovies, MovieType.TRENDING),
            filtered = reduceFilteredMovies(filterAndMapMovies(localMovies, movieTypeFromFilter))
        )
    }

    private suspend fun getUpcomingMoviesRemotely() = resultOf {
        val results = api.getUpcomingMovies().results
        val movies = results.map { it.toDomain() }
        movies
    }

    // TODO: These 3 methods could be refactored as to not repeat code
    private suspend fun getPopularMoviesRemotely() = resultOf {
        val results = api.getPopularMovies().results
        val movies = results.map { it.toDomain() }
        movies
    }

    private suspend fun getMoviesByYearRemotely(year: Int) = resultOf {
        val results = api.getMoviesByYear(year).results
        val movies = results.map { it.toDomain() }
        movies
    }

    private suspend fun getMoviesByLanguageRemotely(language: String) = resultOf {
        val results = api.getMoviesByLanguage(language).results
        val movies = results.map { it.toDomain() }
        movies
    }

    private fun filterAndMapMovies(movies: List<MovieEntity>, movieType: MovieType): List<Movie> {
        return movies.filter { it.type == movieType }.map { it.toDomain() }
    }
}
