package com.mk.moviedb.core.data.mapper

import com.mk.moviedb.core.data.local.entity.MovieEntity
import com.mk.moviedb.core.data.local.entity.MovieType
import com.mk.moviedb.core.data.remote.MovieApi
import com.mk.moviedb.core.data.remote.dto.MovieResult
import com.mk.moviedb.core.domain.model.Movie

fun MovieResult.toDomain(): Movie {
    return Movie(
        id = this.id,
        poster = MovieApi.IMAGE_URL + this.posterPath
    )
}

fun Movie.toEntity(type: MovieType): MovieEntity {
    return MovieEntity(
        id = this.id,
        poster = this.poster,
        type = type
    )
}

fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = this.id,
        poster = this.poster
    )
}
