package com.mk.moviedb.home.presentation

import com.mk.moviedb.core.domain.model.FilterType

sealed class HomeEvent {
    data class ChangeFilter(val filterType: FilterType) : HomeEvent()
}
