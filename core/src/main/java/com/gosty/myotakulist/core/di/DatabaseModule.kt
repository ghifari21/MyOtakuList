package com.gosty.myotakulist.core.di

import android.content.Context
import androidx.room.Room
import com.gosty.myotakulist.core.data.source.local.room.AnimeDao
import com.gosty.myotakulist.core.data.source.local.room.MangaDao
import com.gosty.myotakulist.core.data.source.local.room.MyOtakuListDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MyOtakuListDatabase =
        Room.databaseBuilder(
            context,
            MyOtakuListDatabase::class.java,
            "myotakulist.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideAnimeDao(database: MyOtakuListDatabase): AnimeDao = database.animeDao()

    @Provides
    fun provideMangaDao(database: MyOtakuListDatabase): MangaDao = database.mangaDao()
}