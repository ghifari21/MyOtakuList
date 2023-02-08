package com.gosty.myotakulist.core.domain.usecase

import androidx.paging.PagingData
import com.gosty.myotakulist.core.domain.model.manga.Manga
import com.gosty.myotakulist.core.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MangaInteractor @Inject constructor(
    private val mangaRepository: MangaRepository
) : MangaUseCase {
    override fun getTopManga(): Flow<PagingData<Manga>> = mangaRepository.getTopManga()

    override fun getSearchManga(query: String): Flow<PagingData<Manga>> = mangaRepository.getSearchManga(query)

    override fun getAllFavoriteManga(): Flow<PagingData<Manga>> = mangaRepository.getAllFavoriteManga()


    override suspend fun insertFavoriteManga(manga: Manga) = mangaRepository.insertFavoriteManga(manga)

    override suspend fun deleteFavoriteManga(malId: Int) = mangaRepository.deleteFavoriteManga(malId)
}