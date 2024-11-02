package com.example.movieapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.data.models.toMediaResponse
import com.example.movieapp.data.service.MovieAppService
import com.example.movieapp.domain.models.Media
import retrofit2.HttpException
import java.io.IOException

class TopRatedSeriesSource(private val api: MovieAppService) :
    PagingSource<Int, Media>() {
    override fun getRefreshKey(state: PagingState<Int, Media>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        return try {
            val nextPage = params.key ?: 1
            val topRatedSeries = api.getTopRatedTvSeries(nextPage).toMediaResponse()
            LoadResult.Page(
                data = topRatedSeries.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (topRatedSeries.results.isEmpty()) null else topRatedSeries.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}