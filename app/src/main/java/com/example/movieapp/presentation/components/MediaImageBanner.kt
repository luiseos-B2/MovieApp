package com.example.movieapp.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R

@Composable
fun MediaImageBanner(
    modifier: Modifier = Modifier,
    filmImage: String,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(filmImage)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_placeholder),
        contentDescription = "Movie Banner",
        contentScale = ContentScale.Crop,
        modifier = modifier.fillMaxSize(),
    )
}