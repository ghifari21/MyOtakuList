package com.gosty.myotakulist.core.data.source.remote.network

import com.gosty.myotakulist.core.data.source.remote.response.anime.AnimeResponse
import com.gosty.myotakulist.core.data.source.remote.response.common.CommonPaginationResponse
import com.gosty.myotakulist.core.data.source.remote.response.manga.MangaResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("seasons/now")
    suspend fun getSeasonsNow(
        @Query("page") page: Int
    ): CommonPaginationResponse<AnimeResponse>

    @GET("anime")
    suspend fun getAnimeSearch(
        @Query("q") q: String,
        @Query("page") page: Int
    ): CommonPaginationResponse<AnimeResponse>

    @GET("top/anime")
    suspend fun getTopAnime(
        @Query("page") page: Int
    ): CommonPaginationResponse<AnimeResponse>

    @GET("manga")
    suspend fun getMangaSearch(
        @Query("q") q: String,
        @Query("page") page: Int
    ): CommonPaginationResponse<MangaResponse>

    @GET("top/manga")
    suspend fun getTopManga(
        @Query("page") page: Int
    ): CommonPaginationResponse<MangaResponse>
}