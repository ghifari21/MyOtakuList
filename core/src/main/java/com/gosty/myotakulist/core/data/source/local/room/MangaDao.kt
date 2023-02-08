package com.gosty.myotakulist.core.data.source.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gosty.myotakulist.core.data.source.local.entity.manga.MangaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MangaDao {
    @Query("SELECT * FROM manga")
    fun getAllFavoriteManga(): PagingSource<Int, MangaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteManga(manga: MangaEntity)

    @Query("DELETE FROM manga WHERE malId = :malId")
    suspend fun deleteFavoriteManga(malId: Int)
}