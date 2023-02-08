package com.gosty.myotakulist.core.domain.repository

import androidx.paging.PagingData
import com.gosty.myotakulist.core.domain.model.manga.Manga
import kotlinx.coroutines.flow.Flow

interface MangaRepository {
    fun getSearchManga(query: String): Flow<PagingData<Manga>>
    fun getTopManga(): Flow<PagingData<Manga>>
    fun getAllFavoriteManga(): Flow<PagingData<Manga>>
    suspend fun insertFavoriteManga(manga: Manga)
    suspend fun deleteFavoriteManga(malId: Int)
}