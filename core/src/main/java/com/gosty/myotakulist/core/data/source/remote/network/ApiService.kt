package com.gosty.myotakulist.core.data.source.remote.network

import com.gosty.myotakulist.core.data.source.remote.response.anime.AnimeResponse
import com.gosty.myotakulist.core.data.source.remote.response.common.CommonResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/anime/{id}")
    suspend fun getAnimeDetail(
        @Path("id") id: Int
    ): CommonResponse<AnimeResponse>
}