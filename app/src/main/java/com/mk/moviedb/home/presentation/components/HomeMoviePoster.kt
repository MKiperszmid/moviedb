package com.mk.moviedb.home.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun HomeMoviePoster(
    imageUrl: String,
    posterSize: MoviePosterSize
) {
    val height = if (posterSize == MoviePosterSize.SMALL) 180 else 205
    val width = if (posterSize == MoviePosterSize.SMALL) 138 else 156
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = "poster",
        modifier = Modifier.clip(RoundedCornerShape(8.dp))
            .size(width = (width).dp, height = (height).dp),
        contentScale = ContentScale.FillBounds
    )
}

enum class MoviePosterSize {
    SMALL,
    BIG
}
