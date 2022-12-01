package com.mk.moviedb.core.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.mk.moviedb.core.domain.model.Movie
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class ReduceFilteredMoviesTest {
    private lateinit var reduceFilteredMovies: ReduceFilteredMovies

    @Before
    fun setUp() {
        reduceFilteredMovies = ReduceFilteredMovies()
    }

    @Test
    fun `Empty List, returns Empty List`() {
        val movies = emptyList<Movie>()
        val filtered = reduceFilteredMovies.invoke(movies)

        assertThat(filtered.size).isEqualTo(0)
    }

    @Test
    fun `Three Item List, returns Three Item List`() {
        val movies = listOf<Movie>(
            mockk(relaxed = true),
            mockk(relaxed = true),
            mockk(relaxed = true)
        )
        val filtered = reduceFilteredMovies.invoke(movies)

        assertThat(filtered.size).isEqualTo(3)
    }

    @Test
    fun `Six Item List, returns Six Item List`() {
        val movies = listOf<Movie>(
            mockk(relaxed = true),
            mockk(relaxed = true),
            mockk(relaxed = true),
            mockk(relaxed = true),
            mockk(relaxed = true),
            mockk(relaxed = true)
        )
        val filtered = reduceFilteredMovies.invoke(movies)

        assertThat(filtered.size).isEqualTo(6)
    }

    @Test
    fun `Seven Item List, returns Six Item List`() {
        val movies = listOf<Movie>(
            mockk(relaxed = true),
            mockk(relaxed = true),
            mockk(relaxed = true),
            mockk(relaxed = true),
            mockk(relaxed = true),
            mockk(relaxed = true),
            mockk(relaxed = true)
        )
        val filtered = reduceFilteredMovies.invoke(movies)

        assertThat(filtered.size).isEqualTo(6)
    }
}
