package com.gosty.myotakulist.core.data.source.local

import androidx.paging.PagingSource
import com.gosty.myotakulist.core.data.source.local.entity.anime.AnimeEntity
import com.gosty.myotakulist.core.data.source.local.entity.manga.MangaEntity
import com.gosty.myotakulist.core.data.source.local.room.AnimeDao
import com.gosty.myotakulist.core.data.source.local.room.MangaDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val animeDao: AnimeDao,
    private val mangaDao: MangaDao
) {
    fun getAllFavoriteAnime(): PagingSource<Int, AnimeEntity> = animeDao.getAllFavoriteAnime()

    suspend fun insertFavoriteAnime(anime: AnimeEntity) = animeDao.insertFavoriteAnime(anime)

    suspend fun deleteFavoriteAnime(malId: Int) = animeDao.deleteFavoriteAnime(malId)

    fun getAllFavoriteManga(): PagingSource<Int, MangaEntity> = mangaDao.getAllFavoriteManga()

    suspend fun insertFavoriteManga(manga: MangaEntity) = mangaDao.insertFavoriteManga(manga)

    suspend fun deleteFavoriteManga(malId: Int) = mangaDao.deleteFavoriteManga(malId)
}