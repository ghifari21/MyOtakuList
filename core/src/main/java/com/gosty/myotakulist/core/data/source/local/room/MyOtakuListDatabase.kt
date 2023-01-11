package com.gosty.myotakulist.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gosty.myotakulist.core.data.source.local.entity.anime.AnimeEntity
import com.gosty.myotakulist.core.data.source.local.entity.manga.MangaEntity
import com.gosty.myotakulist.core.utils.GeneralConverter
import com.gosty.myotakulist.core.utils.StringConverter
import com.gosty.myotakulist.core.utils.TitleConverter

@Database(entities = [AnimeEntity::class, MangaEntity::class], version = 1, exportSchema = false)
@TypeConverters(value = [StringConverter::class, TitleConverter::class, GeneralConverter::class])
abstract class MyOtakuListDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
    abstract fun mangaDao(): MangaDao
}