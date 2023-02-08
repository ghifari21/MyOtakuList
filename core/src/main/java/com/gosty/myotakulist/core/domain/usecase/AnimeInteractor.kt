package com.gosty.myotakulist.core.domain.usecase

import androidx.paging.PagingData
import com.gosty.myotakulist.core.domain.model.anime.Anime
import com.gosty.myotakulist.core.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimeInteractor @Inject constructor(
    private val animeRepository: AnimeRepository
) : AnimeUseCase {
    override fun getSeasonsAnime(): Flow<PagingData<Anime>> = animeRepository.getSeasonsAnime()

    override fun getTopAnime(): Flow<PagingData<Anime>> = animeRepository.getTopAnime()

    override fun getSearchAnime(query: String): Flow<PagingData<Anime>> = animeRepository.getSearchAnime(query)

    override fun getAllFavoriteAnime(): Flow<PagingData<Anime>> = animeRepository.getAllFavoriteAnime()


    override suspend fun insertFavoriteAnime(anime: Anime) = animeRepository.insertFavoriteAnime(anime)

    override suspend fun deleteFavoriteAnime(malId: Int) = animeRepository.deleteFavoriteAnime(malId)
}