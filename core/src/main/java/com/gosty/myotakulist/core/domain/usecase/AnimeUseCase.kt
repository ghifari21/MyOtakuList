package com.gosty.myotakulist.core.domain.usecase

import androidx.paging.PagingData
import com.gosty.myotakulist.core.domain.model.anime.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeUseCase {
    fun getSeasonsAnime(): Flow<PagingData<Anime>>
    fun getTopAnime(): Flow<PagingData<Anime>>
    fun getSearchAnime(query: String): Flow<PagingData<Anime>>
    fun getAllFavoriteAnime(): Flow<PagingData<Anime>>
    suspend fun insertFavoriteAnime(anime: Anime)
    suspend fun deleteFavoriteAnime(malId: Int)
}