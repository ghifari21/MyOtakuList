package com.gosty.myotakulist.core.data.source.local.room

import androidx.paging.PagingSource
import androidx.room.*
import com.gosty.myotakulist.core.data.source.local.entity.anime.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Query("SELECT * FROM anime")
    fun getAllFavoriteAnime(): PagingSource<Int, AnimeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteAnime(anime: AnimeEntity)

    @Query("DELETE FROM anime WHERE malId = :malId")
    suspend fun deleteFavoriteAnime(malId: Int)
}