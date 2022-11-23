package com.mk.moviedb.core.data.mapper

import com.mk.moviedb.core.data.remote.MovieApi
import com.mk.moviedb.core.data.remote.dto.MovieResult
import com.mk.moviedb.core.domain.model.Movie

fun MovieResult.toDomain(): Movie {
    return Movie(
        id = this.id,
        description = this.overview,
        title = this.title,
        releaseYear = this.releaseDate.substring(0, 4).toInt(),
        language = this.originalLanguage,
        rating = this.voteAverage,
        poster = MovieApi.IMAGE_URL + this.posterPath,
        genres = this.genreIds
    )
}
