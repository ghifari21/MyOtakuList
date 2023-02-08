package com.gosty.myotakulist.core.data.source.remote.paging.manga

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gosty.myotakulist.core.data.source.remote.network.ApiService
import com.gosty.myotakulist.core.data.source.remote.response.manga.MangaResponse
import okio.IOException
import retrofit2.HttpException

class TopMangaPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, MangaResponse>() {
    override fun getRefreshKey(state: PagingState<Int, MangaResponse>): Int? {
        return state.anchorPosition?.let { pos ->
            val anchorPage = state.closestPageToPosition(pos)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MangaResponse> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getTopManga(position)

            LoadResult.Page(
                data = responseData.data,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.pagination.hasNextPage) position + 1 else null
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}