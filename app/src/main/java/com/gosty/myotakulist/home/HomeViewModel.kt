package com.gosty.myotakulist.home

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gosty.myotakulist.core.domain.model.anime.Anime
import com.gosty.myotakulist.core.domain.model.manga.Manga
import com.gosty.myotakulist.core.domain.usecase.AnimeUseCase
import com.gosty.myotakulist.core.domain.usecase.MangaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val animeUseCase: AnimeUseCase,
    private val mangaUseCase: MangaUseCase
) : ViewModel() {
    fun getSearchAnime(query: String): LiveData<PagingData<Anime>> =
        animeUseCase
            .getSearchAnime(query)
            .cachedIn(viewModelScope)
            .asLiveData()

    fun getSearchManga(query: String): LiveData<PagingData<Manga>> =
        mangaUseCase
            .getSearchManga(query)
            .cachedIn(viewModelScope)
            .asLiveData()
}