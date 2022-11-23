package com.mk.moviedb.home.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun HomeMovieList(
    title: String,
    posters: List<String>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = title, fontWeight = FontWeight.SemiBold, fontSize = 20.sp, color = Color.White)
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(posters) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(it)
                        .crossfade(true)
                        .build(),
                    contentDescription = "poster",
                    modifier = Modifier.clip(RoundedCornerShape(8.dp))
                        .size(width = 138.dp, height = 180.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
