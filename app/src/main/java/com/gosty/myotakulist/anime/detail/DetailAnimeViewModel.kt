package com.gosty.myotakulist.anime.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gosty.myotakulist.core.domain.model.anime.Anime
import com.gosty.myotakulist.core.domain.usecase.AnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailAnimeViewModel @Inject constructor(
    private val animeUseCase: AnimeUseCase
) : ViewModel() {
    fun insertAnimeFavorite(anime: Anime) {
        viewModelScope.launch {
            animeUseCase.insertFavoriteAnime(anime)
        }
    }

    fun deleteAnimeFavorite(malId: Int) {
        viewModelScope.launch {
            animeUseCase.deleteFavoriteAnime(malId)
        }
    }
}