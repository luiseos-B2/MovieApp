package com.example.movieapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.data.models.toMediaResponse
import com.example.movieapp.data.service.MovieAppService
import com.example.movieapp.domain.models.Media
import retrofit2.HttpException
import java.io.IOException

class TrendingSeriesSource(private val api: MovieAppService) :
    PagingSource<Int, Media>() {
    override fun getRefreshKey(state: PagingState<Int, Media>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        return try {
            val nextPage = params.key ?: 1
            val trendingSeriesList = api.getTrendingTvSeries(nextPage).toMediaResponse()
            LoadResult.Page(
                data = trendingSeriesList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (trendingSeriesList.results.isEmpty()) null else trendingSeriesList.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}
