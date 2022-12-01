package com.mk.moviedb.detail.presentation

import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth.assertThat
import com.mk.moviedb.CoroutineRule
import com.mk.moviedb.core.data.FakeRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {
    @get:Rule
    val coroutineRule = CoroutineRule()

    private lateinit var viewmodel: DetailViewModel
    private lateinit var repository: FakeRepository
    private lateinit var savedStateHandle: SavedStateHandle

    @Before
    fun setUp() {
        repository = FakeRepository()
        savedStateHandle = mockk(relaxed = true)
        every { savedStateHandle.get<Int>(any()) } returns repository.movieDetail.id
        viewmodel = DetailViewModel(savedStateHandle, repository)
    }

    @Test
    fun `Get Movie Detail is valid, sets movie on the state`() {
        assertThat(viewmodel.state.isLoading).isTrue()
        coroutineRule.dispatchers.scheduler.advanceUntilIdle()
        assertThat(viewmodel.state.isLoading).isFalse()
        assertThat(viewmodel.state.movie).isNotNull()
        assertThat(viewmodel.state.movie).isEqualTo(repository.movieDetail)
    }
}
