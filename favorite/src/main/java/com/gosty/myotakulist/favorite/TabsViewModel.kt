package com.gosty.myotakulist.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gosty.myotakulist.core.domain.model.anime.Anime
import com.gosty.myotakulist.core.domain.model.manga.Manga
import com.gosty.myotakulist.core.domain.usecase.AnimeUseCase
import com.gosty.myotakulist.core.domain.usecase.MangaUseCase

class TabsViewModel(
    private val animeUseCase: AnimeUseCase,
    private val mangaUseCase: MangaUseCase
) : ViewModel() {
    fun getAllFavoriteAnime(): LiveData<PagingData<Anime>> =
        animeUseCase
            .getAllFavoriteAnime()
            .cachedIn(viewModelScope)
            .asLiveData()

    fun getAllFavoriteManga(): LiveData<PagingData<Manga>> =
        mangaUseCase
            .getAllFavoriteManga()
            .cachedIn(viewModelScope)
            .asLiveData()
}