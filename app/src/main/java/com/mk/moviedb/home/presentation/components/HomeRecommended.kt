package com.mk.moviedb.home.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mk.moviedb.core.domain.model.FilterType

@Composable
fun HomeRecommended(
    selectedFilter: FilterType,
    onFilterClick: (FilterType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        CategoryTitle(title = "Recomendados para ti")
        Spacer(modifier = Modifier.height(12.dp))
        FilterPillContainer(
            filters = FilterType.values().toList(),
            selectedFilter = selectedFilter,
            onFilterClick = onFilterClick
        )
    }
}
