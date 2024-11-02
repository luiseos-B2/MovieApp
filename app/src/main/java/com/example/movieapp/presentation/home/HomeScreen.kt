package com.example.movieapp.presentation.home

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.domain.models.MediaType
import com.example.movieapp.presentation.util.createImageUrl
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.MediaDetailsScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalSharedTransitionApi::class)
@Destination<RootGraph>(start = true)
@Composable
fun SharedTransitionScope.HomeScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeUiState by viewModel.homeUiState.collectAsState()

    HomeScreenContent(
        state = homeUiState,
        animatedVisibilityScope = animatedVisibilityScope,
        onEvent = { event ->
            when (event) {
                is HomeUiEvents.NavigateBack -> {
                    navigator.navigateUp()
                }

                is HomeUiEvents.NavigateToMediaDetails -> {
                    navigator.navigate(
                        MediaDetailsScreenDestination(
                            value = event.media,
                            mediaType = event.mediaType,
                        )
                    )
                }

                is HomeUiEvents.OnMediaGenreSelected -> {
                    when (homeUiState.mediaType) {
                        MediaType.TV_SHOW -> {
                            viewModel.setGenre(event.genre)
                            viewModel.getTrendingTvSeries(event.genre.id)
                            viewModel.getTopRatedTvSeries(event.genre.id)
                            viewModel.getAiringTodayTvSeries(event.genre.id)
                            viewModel.getOnTheAirTvSeries(event.genre.id)
                            viewModel.getPopularTvSeries(event.genre.id)
                        }
                        MediaType.MOVIE -> {
                            viewModel.setGenre(event.genre)
                            viewModel.getTrendingMovies(event.genre.id)
                            viewModel.getTopRatedMovies(event.genre.id)
                            viewModel.getUpcomingMovies(event.genre.id)
                            viewModel.getNowPayingMovies(event.genre.id)
                            viewModel.getPopularMovies(event.genre.id)
                        }
                    }
                }

                is HomeUiEvents.OnMediaOptionSelected -> {
                    viewModel.setSelectedOption(event.mediaType)
                }

                HomeUiEvents.OnPullToRefresh -> {
                    viewModel.refreshAllData()
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeScreenContent(
    state: HomeUiState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onEvent: (HomeUiEvents) -> Unit,
) {
    val context = LocalContext.current
    val trendingMovies = state.trendingMovies.collectAsLazyPagingItems()
    val upcomingMovies = state.upcomingMovies.collectAsLazyPagingItems()
    val topRatedMovies = state.topRatedMovies.collectAsLazyPagingItems()
    val nowPlayingMovies = state.nowPlayingMovies.collectAsLazyPagingItems()
    val popularMovies = state.popularMovies.collectAsLazyPagingItems()
    val trendingTvSeries = state.trendingTvSeries.collectAsLazyPagingItems()
    val onAirTvSeries = state.onAirTvSeries.collectAsLazyPagingItems()
    val topRatedTvSeries = state.topRatedTvSeries.collectAsLazyPagingItems()
    val airingTodayTvSeries = state.airingTodayTvSeries.collectAsLazyPagingItems()
    val popularTvSeries = state.popularTvSeries.collectAsLazyPagingItems()

    Scaffold{ innerPadding ->
        PullToRefreshBox(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            isRefreshing = false,
            onRefresh = {
                onEvent(HomeUiEvents.OnPullToRefresh)
            }
        ) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    FilmCategory(
                        items = listOf(
                            MediaType.MOVIE.name,
                            MediaType.TV_SHOW.name
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        state = state,
                        onEvent = onEvent,
                    )
                }

                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.genres),
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Genres(
                            state = state,
                            onEvent = onEvent,
                        )
                    }
                }

                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = stringResource(R.string.trending_today),
                            style = MaterialTheme.typography.titleMedium,
                        )

                        when (state.mediaType) {
                            MediaType.MOVIE -> {
                                PagedRow(
                                    items = trendingMovies,
                                    modifier = Modifier.fillMaxWidth(),
                                    content = {
                                        FilmItem(
                                            modifier = Modifier
                                                .height(200.dp)
                                                .width(230.dp)
                                                .clickable {
                                                    onEvent(
                                                        HomeUiEvents.NavigateToMediaDetails(
                                                            media = it,
                                                            mediaType = state.mediaType,
                                                        )
                                                    )
                                                },
                                            imageUrl = it.posterPath.createImageUrl(),
                                            animatedVisibilityScope = animatedVisibilityScope,
                                            sharedTransitionKey = "${it.id}_${context.getString(R.string.trending_today)}",
                                        )
                                    }
                                )
                            }
                            MediaType.TV_SHOW -> {
                                PagedRow(
                                    items = trendingTvSeries,
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    content = {
                                        FilmItem(
                                            modifier = Modifier
                                                .height(220.dp)
                                                .width(250.dp)
                                                .clickable {
                                                    onEvent(
                                                        HomeUiEvents.NavigateToMediaDetails(
                                                            media = it,
                                                            mediaType = state.mediaType,
                                                        )
                                                    )
                                                },
                                            imageUrl = it.posterPath.createImageUrl(),
                                            animatedVisibilityScope = animatedVisibilityScope,
                                            sharedTransitionKey = "${it.id}_${context.getString(R.string.trending_today)}",
                                        )
                                    }
                                )
                            }
                        }
                    }
                }

                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = stringResource(R.string.popular),
                            style = MaterialTheme.typography.titleMedium,
                        )

                        when (state.mediaType) {
                            MediaType.MOVIE -> {
                                PagedRow(
                                    items = popularMovies,
                                    modifier = Modifier.fillMaxWidth(),
                                    content = {
                                        FilmItem(
                                            modifier = Modifier
                                                .height(200.dp)
                                                .width(130.dp)
                                                .clickable {
                                                    onEvent(
                                                        HomeUiEvents.NavigateToMediaDetails(
                                                            media = it,
                                                            mediaType = state.mediaType,
                                                        )
                                                    )
                                                },
                                            imageUrl = it.posterPath.createImageUrl(),
                                            animatedVisibilityScope = animatedVisibilityScope,
                                            sharedTransitionKey = "${it.id}_${context.getString(R.string.popular)}",
                                        )
                                    }
                                )
                            }
                            MediaType.TV_SHOW -> {
                                PagedRow(
                                    items = popularTvSeries,
                                    modifier = Modifier.fillMaxWidth(),
                                    content = {
                                        FilmItem(
                                            modifier = Modifier
                                                .height(200.dp)
                                                .width(130.dp)
                                                .clickable {
                                                    onEvent(
                                                        HomeUiEvents.NavigateToMediaDetails(
                                                            media = it,
                                                            mediaType = state.mediaType,
                                                        )
                                                    )
                                                },
                                            imageUrl = it.posterPath.createImageUrl(),
                                            animatedVisibilityScope = animatedVisibilityScope,
                                            sharedTransitionKey = "${it.id}_${context.getString(R.string.popular)}",
                                        )
                                    }
                                )
                            }
                        }
                    }
                }


                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = when (state.mediaType) {
                                MediaType.MOVIE -> {
                                    stringResource(R.string.upcoming)
                                }

                                MediaType.TV_SHOW -> {
                                    stringResource(R.string.on_air)
                                }
                            },
                            style = MaterialTheme.typography.titleMedium,
                        )
                        when (state.mediaType) {
                            MediaType.MOVIE -> {
                                PagedRow(
                                    items = upcomingMovies,
                                    modifier = Modifier.fillMaxWidth(),
                                    content = {
                                        FilmItem(
                                            modifier = Modifier
                                                .height(200.dp)
                                                .width(130.dp)
                                                .clickable {
                                                    onEvent(
                                                        HomeUiEvents.NavigateToMediaDetails(
                                                            media = it,
                                                            mediaType = state.mediaType,
                                                        )
                                                    )
                                                },
                                            imageUrl = it.posterPath.createImageUrl(),
                                            animatedVisibilityScope = animatedVisibilityScope,
                                            sharedTransitionKey = "${it.id}_${context.getString(R.string.upcoming)}",
                                        )
                                    }
                                )
                            }
                            MediaType.TV_SHOW -> {
                                PagedRow(
                                    items = onAirTvSeries,
                                    modifier = Modifier.fillMaxWidth(),
                                    content = {
                                        FilmItem(
                                            modifier = Modifier
                                                .height(200.dp)
                                                .width(130.dp)
                                                .clickable {
                                                    onEvent(
                                                        HomeUiEvents.NavigateToMediaDetails(
                                                            media = it,
                                                            mediaType = state.mediaType,
                                                        )
                                                    )
                                                },
                                            imageUrl = it.posterPath.createImageUrl(),
                                            animatedVisibilityScope = animatedVisibilityScope,
                                            sharedTransitionKey = "${it.id}_${context.getString(R.string.on_air)}",
                                        )
                                    }
                                )
                            }
                        }
                    }
                }

                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = when (state.mediaType) {
                                MediaType.MOVIE -> {
                                    stringResource(R.string.airing_today)
                                }

                                MediaType.TV_SHOW -> {
                                    stringResource(R.string.now_playing)
                                }
                            },
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                    when (state.mediaType) {
                        MediaType.MOVIE -> {
                            PagedRow(
                                items = nowPlayingMovies,
                                modifier = Modifier.fillMaxWidth(),
                                content = {
                                    FilmItem(
                                        modifier = Modifier
                                            .height(200.dp)
                                            .width(130.dp)
                                            .clickable {
                                                onEvent(
                                                    HomeUiEvents.NavigateToMediaDetails(
                                                        media = it,
                                                        mediaType = state.mediaType,
                                                    )
                                                )
                                            },
                                        imageUrl = it.posterPath.createImageUrl(),
                                        animatedVisibilityScope = animatedVisibilityScope,
                                        sharedTransitionKey = "${it.id}_${context.getString(R.string.now_playing)}",
                                    )
                                }
                            )
                        }
                        MediaType.TV_SHOW -> {
                            PagedRow(
                                items = airingTodayTvSeries,
                                modifier = Modifier.fillMaxWidth(),
                                content = {
                                    FilmItem(
                                        modifier = Modifier
                                            .height(200.dp)
                                            .width(130.dp)
                                            .clickable {
                                                onEvent(
                                                    HomeUiEvents.NavigateToMediaDetails(
                                                        media = it,
                                                        mediaType = state.mediaType,
                                                    )
                                                )
                                            },
                                        imageUrl = it.posterPath.createImageUrl(),
                                        animatedVisibilityScope = animatedVisibilityScope,
                                        sharedTransitionKey = "${it.id}_${context.getString(R.string.airing_today)}",
                                    )
                                }
                            )
                        }
                    }
                }

                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = stringResource(R.string.top_rated),
                            style = MaterialTheme.typography.titleMedium,
                        )
                        when (state.mediaType) {
                            MediaType.MOVIE -> {
                                PagedRow(
                                    items = topRatedMovies,
                                    modifier = Modifier.fillMaxWidth(),
                                    content = {
                                        FilmItem(
                                            modifier = Modifier
                                                .height(200.dp)
                                                .width(130.dp)
                                                .clickable {
                                                    onEvent(
                                                        HomeUiEvents.NavigateToMediaDetails(
                                                            media = it,
                                                            mediaType = state.mediaType,
                                                        )
                                                    )
                                                },
                                            imageUrl = it.posterPath.createImageUrl(),
                                            animatedVisibilityScope = animatedVisibilityScope,
                                            sharedTransitionKey = "${it.id}_${context.getString(R.string.top_rated)}",
                                        )
                                    }
                                )
                            }
                            MediaType.TV_SHOW -> {
                                PagedRow(
                                    items = topRatedTvSeries,
                                    modifier = Modifier.fillMaxWidth(),
                                    content = {
                                        FilmItem(
                                            modifier = Modifier
                                                .height(200.dp)
                                                .width(130.dp)
                                                .clickable {
                                                    onEvent(
                                                        HomeUiEvents.NavigateToMediaDetails(
                                                            media = it,
                                                            mediaType = state.mediaType,
                                                        )
                                                    )
                                                },
                                            imageUrl = it.posterPath.createImageUrl(),
                                            animatedVisibilityScope = animatedVisibilityScope,
                                            sharedTransitionKey = "${it.id}_${context.getString(R.string.top_rated)}",
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.FilmItem(
    modifier: Modifier = Modifier,
    sharedTransitionKey: String,
    animatedVisibilityScope: AnimatedVisibilityScope,
    imageUrl: String,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_placeholder),
        error = painterResource(id = R.drawable.ic_placeholder),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .sharedElement(
                state = rememberSharedContentState(
                    key = sharedTransitionKey
                ),
                animatedVisibilityScope = animatedVisibilityScope,
            )
            .fillMaxSize()
            .clip(shape = MaterialTheme.shapes.medium)
    )
}

@Composable
fun FilmCategory(
    items: List<String>,
    modifier: Modifier = Modifier,
    state: HomeUiState,
    onEvent: (HomeUiEvents) -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        items.forEach { item ->
            val lineLength = animateFloatAsState(
                targetValue = if (item == state.mediaType.name) 2f else 0f,
                animationSpec = tween(
                    durationMillis = 300
                ),
                label = "lineLength"
            )

            val primaryColor = MaterialTheme.colorScheme.primary
            Text(
                text = item,
                modifier = Modifier
                    .padding(8.dp)
                    .drawBehind {
                        if (item == state.mediaType.name) {
                            if (lineLength.value > 0f) {
                                drawLine(
                                    color = primaryColor,
                                    start = Offset(
                                        size.width / 2f - lineLength.value * 10.dp.toPx(),
                                        size.height
                                    ),
                                    end = Offset(
                                        size.width / 2f + lineLength.value * 10.dp.toPx(),
                                        size.height
                                    ),
                                    strokeWidth = 2.dp.toPx(),
                                    cap = StrokeCap.Round
                                )
                            }
                        }
                    }
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        onEvent(HomeUiEvents.OnMediaOptionSelected(if (item == MediaType.MOVIE.name) MediaType.MOVIE else MediaType.TV_SHOW))
                    },
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = if (item == state.mediaType.name) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onBackground.copy(
                        .5f
                    ),
                )
            )
        }
    }
}

@Composable
fun Genres(
    state: HomeUiState,
    onEvent: (HomeUiEvents) -> Unit,
) {
    val genres = when (state.mediaType) {
        MediaType.MOVIE -> {
            state.moviesGenres
        }
        MediaType.TV_SHOW -> {
            state.tvSeriesGenres
        }
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = genres) { genre ->
            val isSelected = genre.name == state.selectedGenre?.name

            Box(
                modifier = Modifier
                    .background(
                        color = if (isSelected) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.background
                        },
                        shape = MaterialTheme.shapes.medium
                    )
                    .border(
                        width = .3.dp,
                        shape = MaterialTheme.shapes.medium,
                        color = if (isSelected) {
                            MaterialTheme.colorScheme.background
                        } else {
                            MaterialTheme.colorScheme.onBackground.copy(.5f)
                        }
                    )
                    .clickable {
                        onEvent(
                            HomeUiEvents.OnMediaGenreSelected(
                                genre = genre,
                                mediaType = state.mediaType,
                            )
                        )
                    }
            ) {
                Text(
                    text = genre.name,
                    modifier = Modifier
                        .padding(
                            horizontal = 8.dp,
                            vertical = 4.dp
                        ),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = if (isSelected) {
                            MaterialTheme.colorScheme.onPrimary
                        } else {
                            MaterialTheme.colorScheme.onBackground
                        }
                    )
                )
            }
        }
    }
}

@Composable
fun <T : Any> PagedRow(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<T>,
    content: @Composable (T) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(210.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items.itemCount) {
                val item = items[it]
                if (item != null) {
                    content(item)
                }
            }

            items.loadState.let { loadState ->
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillParentMaxWidth()
                                    .align(Alignment.Center),
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                CircularProgressIndicator(
                                    strokeWidth = 2.dp,
                                )
                            }
                        }
                    }

                    loadState.refresh is LoadState.NotLoading && items.itemCount < 1 -> {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillParentMaxWidth()
                                    .align(Alignment.Center),
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = "No data available",
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }


                    loadState.refresh is LoadState.Error -> {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillParentMaxWidth()
                                    .align(Alignment.Center),
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = when ((loadState.refresh as LoadState.Error).error) {
                                        is HttpException -> {
                                            "Oops, something went wrong!"
                                        }

                                        is IOException -> {
                                            "Couldn't reach server, check your internet connection!"
                                        }

                                        else -> {
                                            "Unknown error occurred"
                                        }
                                    },
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.primary,
                                )
                            }
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .size(16.dp)
                                        .align(Alignment.Center),
                                    strokeWidth = 2.dp,
                                )
                            }
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "An error occurred",
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
