package com.mk.moviedb.core.data.remote

import com.mk.moviedb.core.data.remote.dto.MovieDtoResponse
import retrofit2.http.GET

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
}
