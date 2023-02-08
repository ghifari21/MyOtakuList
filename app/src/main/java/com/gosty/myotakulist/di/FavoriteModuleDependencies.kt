package com.gosty.myotakulist.di

import com.gosty.myotakulist.core.domain.usecase.AnimeUseCase
import com.gosty.myotakulist.core.domain.usecase.MangaUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun animeUseCase(): AnimeUseCase
    fun mangaUseCase(): MangaUseCase
}