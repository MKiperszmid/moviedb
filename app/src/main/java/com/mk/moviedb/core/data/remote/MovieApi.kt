package com.mk.moviedb.core.data.remote

import com.mk.moviedb.core.data.remote.dto.MovieDetailResponse
import com.mk.moviedb.core.data.remote.dto.MovieDtoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    companion object {
        const val IMAGE_URL = "https://image.tmdb.org/t/p/original/"
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    // TODO: Pagination

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): MovieDtoResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieDtoResponse

    @GET("discover/movie?sort_by=popularity.desc&include_adult=false")
    suspend fun getMoviesByYear(@Query("year") year: Int): MovieDtoResponse

    @GET("discover/movie?sort_by=popularity.desc&include_adult=false")
    suspend fun getMoviesByLanguage(@Query("with_original_language") language: String): MovieDtoResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_id") movieId: Int): MovieDetailResponse
}
