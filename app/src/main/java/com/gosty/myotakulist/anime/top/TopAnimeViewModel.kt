package com.gosty.myotakulist.anime.top

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gosty.myotakulist.core.domain.model.anime.Anime
import com.gosty.myotakulist.core.domain.usecase.AnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopAnimeViewModel @Inject constructor(
    private val animeUseCase: AnimeUseCase
) : ViewModel() {
    fun getTopAnime(): LiveData<PagingData<Anime>> =
        animeUseCase
            .getTopAnime()
            .cachedIn(viewModelScope)
            .asLiveData()
}