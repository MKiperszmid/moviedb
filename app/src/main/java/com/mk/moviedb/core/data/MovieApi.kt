package com.mk.moviedb.core.data

interface MovieApi {
    companion object {
        const val BASE_URL =
            "https://api.themoviedb.org/4/list/{list_id}?page=1"
    }
}
