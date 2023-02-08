package com.gosty.myotakulist.core.data.source.remote

import androidx.paging.PagingSource
import com.gosty.myotakulist.core.data.source.remote.network.ApiService
import com.gosty.myotakulist.core.data.source.remote.paging.anime.SearchAnimePagingSource
import com.gosty.myotakulist.core.data.source.remote.paging.anime.SeasonsAnimePagingSource
import com.gosty.myotakulist.core.data.source.remote.paging.anime.TopAnimePagingSource
import com.gosty.myotakulist.core.data.source.remote.paging.manga.SearchMangaPagingSource
import com.gosty.myotakulist.core.data.source.remote.paging.manga.TopMangaPagingSource
import com.gosty.myotakulist.core.data.source.remote.response.anime.AnimeResponse
import com.gosty.myotakulist.core.data.source.remote.response.manga.MangaResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    fun getSeasonsAnime(): PagingSource<Int, AnimeResponse> {
        return SeasonsAnimePagingSource(apiService)
    }

    fun getTopAnime(): PagingSource<Int, AnimeResponse> {
        return TopAnimePagingSource(apiService)
    }

    fun getSearchAnime(query: String): PagingSource<Int, AnimeResponse> {
        return SearchAnimePagingSource(apiService, query)
    }

    fun getSearchManga(query: String): PagingSource<Int, MangaResponse> {
        return SearchMangaPagingSource(apiService, query)
    }

    fun getTopManga(): PagingSource<Int, MangaResponse> {
        return TopMangaPagingSource(apiService)
    }
}