package com.example.movieapp.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.R
import com.example.movieapp.domain.models.Media
import com.example.movieapp.presentation.details.MediaDetailsUiEvents

@Composable
fun MediaActions(
    modifier: Modifier = Modifier,
    onEvents: (MediaDetailsUiEvents) -> Unit,
    isLiked: Boolean,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CircleButton(
            onClick = {
                onEvents(MediaDetailsUiEvents.NavigateBack)
            },
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_left),
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = null
            )
        }

        CircleButton(
            onClick = {

            },
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                tint = if (isLiked) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.background
                },
                contentDescription = if (isLiked) {
                    stringResource(id = R.string.unlike)
                } else {
                    stringResource(id = R.string.like)
                }
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MediaActionsPreview() {
    MediaActions(
        onEvents = {},
        isLiked = true,
    )
}
