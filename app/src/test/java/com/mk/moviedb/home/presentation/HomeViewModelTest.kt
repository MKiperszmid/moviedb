package com.mk.moviedb.home.presentation

import com.google.common.truth.Truth.assertThat
import com.mk.moviedb.CoroutineRule
import com.mk.moviedb.core.data.FakeRepository
import com.mk.moviedb.core.domain.model.FilterType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    @get:Rule
    val coroutineRule = CoroutineRule()

    private lateinit var viewmodel: HomeViewModel
    private lateinit var repository: FakeRepository

    @Before
    fun setUp() {
        repository = FakeRepository()
        viewmodel = HomeViewModel(repository)
    }

    // It's not really recommended to do many asserts in one function, but it's to test the state

    @Test
    fun `Get Movies is valid, sets movies on the state`() {
        assertThat(viewmodel.state.isLoading).isTrue()
        coroutineRule.dispatchers.scheduler.advanceUntilIdle()
        assertThat(viewmodel.state.isLoading).isFalse()
        assertThat(viewmodel.state.upcomingMovies.size).isEqualTo(3)
        assertThat(viewmodel.state.popularMovies.size).isEqualTo(2)
        assertThat(viewmodel.state.filteredMovies.size).isEqualTo(4)
    }

    @Test
    fun `Get Movies is valid with ninety three filter, sets movies on the state`() {
        assertThat(viewmodel.state.isLoading).isTrue()
        viewmodel.onEvent(HomeEvent.ChangeFilter(FilterType.NINETY_THREE))
        coroutineRule.dispatchers.scheduler.advanceUntilIdle()
        assertThat(viewmodel.state.isLoading).isFalse()
        assertThat(viewmodel.state.upcomingMovies.size).isEqualTo(3)
        assertThat(viewmodel.state.popularMovies.size).isEqualTo(2)
        assertThat(viewmodel.state.filteredMovies.size).isEqualTo(5)
    }

    @Test
    fun `Get Movies is valid with filter change, sets movies on the state`() {
        assertThat(viewmodel.state.isLoading).isTrue()
        coroutineRule.dispatchers.scheduler.advanceUntilIdle()
        assertThat(viewmodel.state.isLoading).isFalse()
        assertThat(viewmodel.state.upcomingMovies.size).isEqualTo(3)
        assertThat(viewmodel.state.popularMovies.size).isEqualTo(2)
        assertThat(viewmodel.state.filteredMovies.size).isEqualTo(4)
        viewmodel.onEvent(HomeEvent.ChangeFilter(FilterType.NINETY_THREE))
        coroutineRule.dispatchers.scheduler.advanceUntilIdle()
        assertThat(viewmodel.state.upcomingMovies.size).isEqualTo(3)
        assertThat(viewmodel.state.popularMovies.size).isEqualTo(2)
        assertThat(viewmodel.state.filteredMovies.size).isEqualTo(5)
    }
}
