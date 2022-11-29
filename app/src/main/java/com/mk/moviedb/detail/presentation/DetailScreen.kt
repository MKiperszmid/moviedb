package com.mk.moviedb.detail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mk.moviedb.detail.presentation.components.DetailDescriptor

@Composable
fun DetailScreen(
    onBack: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (state.movie != null) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(state.movie.image)
                    .crossfade(true)
                    .build(),
                contentDescription = "background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = state.movie.name,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 40.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    DetailDescriptor(
                        text = state.movie.year.toString(),
                        backgroundColor = Color.LightGray
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    DetailDescriptor(text = state.movie.language, backgroundColor = Color.LightGray)
                    Spacer(modifier = Modifier.width(8.dp))
                    DetailDescriptor(
                        text = state.movie.rating.toString(),
                        backgroundColor = Color.Yellow,
                        image = Icons.Default.Star
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                var formattedGenres = ""
                state.movie.genres.forEachIndexed { index, genre ->
                    formattedGenres += genre
                    if (index + 1 < state.movie.genres.size) {
                        formattedGenres += " • "
                    }
                }
                Text(text = formattedGenres, color = Color.White)
                Spacer(modifier = Modifier.height(32.dp))
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .border(0.4.dp, Color.White, RoundedCornerShape(12.dp))
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Ver Trailer",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "MOVIE PLOT",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = state.movie.description,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
        Box(
            modifier = Modifier.fillMaxSize().background(
                Brush.verticalGradient(
                    colors = listOf(Color.Black, Color.Transparent, Color.Black.copy(alpha = 0.6f)),
                    tileMode = TileMode.Clamp
                )
            )
        )
        IconButton(onClick = { onBack() }, modifier = Modifier.align(Alignment.TopStart)) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "back",
                tint = Color.White
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
