package com.example.movieapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R

@Composable
fun CastItem(
    modifier: Modifier = Modifier,
    imageSize: Dp,
    castName: String,
    castImageUrl: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier.clickable {
            onClick()
        },
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(castImageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_placeholder),
            error = painterResource(id = R.drawable.ic_placeholder),
            contentDescription = castName,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(imageSize)
                .clip(CircleShape)
        )

        Text(
            text = castName,
            color = MaterialTheme.colorScheme.background.copy(.5f),
            fontWeight = FontWeight.ExtraLight,
            fontSize = 11.sp
        )
    }
}