package com.example.movieapp.presentation.details

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.domain.models.Media
import com.example.movieapp.domain.models.MediaType
import com.example.movieapp.presentation.components.CastDetails
import com.example.movieapp.presentation.components.Genres
import com.example.movieapp.presentation.components.MediaActions
import com.example.movieapp.presentation.components.MediaImageBanner
import com.example.movieapp.presentation.components.MediaInfo
import com.example.movieapp.presentation.components.MediaNameAndRating
import com.example.movieapp.presentation.util.createImageUrl
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalSharedTransitionApi::class)
@Destination<RootGraph>
@Composable
fun SharedTransitionScope.MediaDetailsScreen(
    value: Media,
    mediaType: MediaType,
    navigator: DestinationsNavigator,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: MediaDetailsViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getMediaDetails(
            mediaType = mediaType,
            mediaId = value.id
        )
    }
    val filmDetailsUiState by viewModel.filmDetailsUiState.collectAsState()

    MediaDetailsScreenContent(
        media = value,
        isLiked = false,
        mediaType = mediaType,
        state = filmDetailsUiState,
        animatedVisibilityScope = animatedVisibilityScope,
        onEvents = { event ->
            when (event) {
                is MediaDetailsUiEvents.NavigateBack -> {
                    navigator.popBackStack()
                }
            }
        }
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MediaDetailsScreenContent(
    media: Media,
    mediaType: MediaType,
    state: MediaDetailsUiState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onEvents: (MediaDetailsUiEvents) -> Unit,
    isLiked: Boolean,
) {
    Scaffold(
        modifier = Modifier.sharedBounds(
            sharedContentState = rememberSharedContentState(key =  "${media.id}"),
            animatedVisibilityScope = animatedVisibilityScope,
        ),
        topBar = {
            MediaActions(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                onEvents = onEvents,
                isLiked = isLiked,
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                MediaImageBanner(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp),
                    filmImage = media.posterPath.createImageUrl(),
                )
            }

            item {
                MediaNameAndRating(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    filmName = media.name,
                    rating = media.voteAverage.toFloat(),
                )
            }


            item {
                MediaInfo(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    mediaOverview = media.overview,
                    mediaReleaseDate = media.releaseDate,
                )
            }


            if (state.isLoading.not() &&
                ((mediaType == MediaType.TV_SHOW && state.mediaDetails != null) || (mediaType == MediaType.MOVIE && state.mediaDetails != null)) &&
                state.error == null
            ) {
                item {
                    Genres(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        mediaType = mediaType,
                        state = state
                    )
                }
            }

            item {
                CastDetails(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    state = state,
                    onEvent = onEvents,
                )
            }

            if (state.isLoading) {
                item {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            if (
                state.isLoading.not() &&
                state.error != null
            ) {
                item {
                    Text(
                        text = state.error,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
fun SharedTransitionScope.FilmDetailsScreenPreview() {
    MediaDetailsScreenContent(
        media = Media(
            id = 1,
            posterPath = "https://image.tmdb.org/t/p/w500/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
            name = "The Tomorrow War",
            voteAverage = 7.5,
            releaseDate = "2021-07-02",
            overview = "The world is stunned when a group of time travelers arrive from the year 2051 to deliver an urgent message: Thirty years in the future, mankind is losing a global war against a deadly alien species. The only hope for survival is for soldiers and civilians from the present to be transported to the future and join the fight. Among those recruited is high school",
        ),
        mediaType = MediaType.TV_SHOW,
        state = MediaDetailsUiState(),
        onEvents = {},
        isLiked = false,
        animatedVisibilityScope = this as AnimatedVisibilityScope
    )
}
