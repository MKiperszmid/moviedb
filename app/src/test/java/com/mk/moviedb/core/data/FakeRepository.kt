package com.mk.moviedb.core.data

import com.mk.moviedb.core.domain.model.FilterType
import com.mk.moviedb.core.domain.model.Movie
import com.mk.moviedb.core.domain.model.MovieDetail
import com.mk.moviedb.core.domain.model.MovieList
import com.mk.moviedb.core.domain.repository.MovieRepository
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository : MovieRepository {
    private val trendingMovies = listOf<Movie>(
        mockk(relaxed = true),
        mockk(relaxed = true)
    )

    private val upcomingMovies = listOf<Movie>(
        mockk(relaxed = true),
        mockk(relaxed = true),
        mockk(relaxed = true)
    )

    private val spanishMovies = listOf<Movie>(
        mockk(relaxed = true),
        mockk(relaxed = true),
        mockk(relaxed = true),
        mockk(relaxed = true)
    )

    private val ninetyThreeMovies = listOf<Movie>(
        mockk(relaxed = true),
        mockk(relaxed = true),
        mockk(relaxed = true),
        mockk(relaxed = true),
        mockk(relaxed = true)
    )

    val movieDetail = MovieDetail(
        id = 12345,
        image = "image.com",
        name = "Movie Name",
        year = 2023,
        language = "ES",
        rating = 9.6,
        genres = listOf("Comedy", "Action"),
        description = "This is a description"
    )

    override fun getAllMovies(filterType: FilterType, isFilteredOnly: Boolean): Flow<MovieList> {
        return flow {
            emit(
                MovieList(
                    upcoming = upcomingMovies,
                    trending = trendingMovies,
                    filtered = if (filterType == FilterType.NINETY_THREE) ninetyThreeMovies else spanishMovies
                )
            )
        }
    }

    override suspend fun getMovieById(id: Int): Result<MovieDetail> {
        return Result.success(movieDetail)
    }
}
