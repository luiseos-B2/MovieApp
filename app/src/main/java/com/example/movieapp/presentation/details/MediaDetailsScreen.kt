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
import com.example.movieapp.data.models.GenreApi
import com.example.movieapp.domain.models.Cast
import com.example.movieapp.domain.models.CastResponse
import com.example.movieapp.domain.models.Media
import com.example.movieapp.domain.models.MediaDetails
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
            adult = false,
            backdropPath = "/tX1hE01H2Ye3Q60g6pWCn4kY1.jpg",
            firstAirDate = "2023-11-17",
            genreIds = listOf(18, 10759),
            id = 109449,
            name = "The Mandalorian",
            originCountry = listOf("US"),
            originalLanguage = "en",
            originalName = "The Mandalorian",
            overview = "Set after the fall of the Galactic Empire, and before the emergence of the First Order, The Mandalorian follows the travails of a lone gunfighter in the outer reaches of the galaxy.",
            popularity = 875.322,
            posterPath = "/9xqv0NYfeiSOKz0fPLbO9QhXw0.jpg",
            voteAverage = 8.3,
            voteCount = 11178,
            releaseDate = ""
        ),
        mediaType = MediaType.TV_SHOW,
        state = MediaDetailsUiState(
            casts = CastResponse(
                cast = listOf(
                    Cast(
                        id = 123,
                        name = "Mark Hamill",
                        profilePath = "/some/path/to/image.jpg",
                        character = "Luke Skywalker"
                    ),
                    Cast(
                        id = 123,
                        name = "Mark Hamill",
                        profilePath = "/some/path/to/image.jpg",
                        character = "Luke Skywalker"
                    ),
                    Cast(
                        id = 123,
                        name = "Mark Hamill",
                        profilePath = "/some/path/to/image.jpg",
                        character = "Luke Skywalker"
                    ),
                    Cast(
                        id = 123,
                        name = "Mark Hamill",
                        profilePath = "/some/path/to/image.jpg",
                        character = "Luke Skywalker"
                    ),
                )
            ),
            isLoading = false,
            isLoadingCasts = false,
            error = null,
            errorCasts = null,
            mediaDetails = MediaDetails(
                adult = false,
                backdropPath = "/tX1hE01H2Ye3Q60g6pWCn4kY1.jpg",
                budget = 100000000,
                genres = listOf(
                    GenreApi(id = 28, name = "Action"),
                    GenreApi(id = 12, name = "Adventure"),
                    GenreApi(id = 878, name = "Science Fiction")
                ),
                homepage = "https://www.starwars.com/",
                id = 11,
                imdbId = "tt2379713",
                originalLanguage = "en",
                originalTitle = "Star Wars: The Force Awakens",
                overview = "Thirty years after defeating the Galactic Empire, Han Solo and Chewbacca team up with a new generation of heroes—Rey, Finn, and Poe Dameron—to defeat the evil First Order.",
                popularity = 149.857,
                posterPath = "/d36BcFteHbeJXFXdMhAuDA7FMZM.jpg",
                releaseDate = "2015-12-15",
                revenue = 2068223628,
                runtime = 138,
                status = "Released",
                tagline = "The Force Awakens",
                title = "Star Wars: The Force Awakens",
                video = false,
                voteAverage = 7.9,
                voteCount = 12163
            )
        ),
        onEvents = {},
        isLiked = false,
        animatedVisibilityScope = this as AnimatedVisibilityScope
    )
}
