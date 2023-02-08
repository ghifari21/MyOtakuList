package com.gosty.myotakulist.di

import com.gosty.myotakulist.core.domain.usecase.AnimeInteractor
import com.gosty.myotakulist.core.domain.usecase.AnimeUseCase
import com.gosty.myotakulist.core.domain.usecase.MangaInteractor
import com.gosty.myotakulist.core.domain.usecase.MangaUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideAnimeUseCase(animeInteractor: AnimeInteractor): AnimeUseCase

    @Binds
    @Singleton
    abstract fun provideMangaUseCase(mangaInteractor: MangaInteractor): MangaUseCase
}