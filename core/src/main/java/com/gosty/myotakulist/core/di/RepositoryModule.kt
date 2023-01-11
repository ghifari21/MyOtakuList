package com.gosty.myotakulist.core.di

import com.gosty.myotakulist.core.data.AnimeRepositoryImpl
import com.gosty.myotakulist.core.data.MangaRepositoryImpl
import com.gosty.myotakulist.core.domain.repository.AnimeRepository
import com.gosty.myotakulist.core.domain.repository.MangaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideAnimeRepository(repository: AnimeRepositoryImpl): AnimeRepository

    @Binds
    abstract fun provideMangaRepository(repository: MangaRepositoryImpl): MangaRepository
}