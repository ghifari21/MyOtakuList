package com.gosty.myotakulist.manga.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gosty.myotakulist.core.domain.model.manga.Manga
import com.gosty.myotakulist.core.domain.usecase.MangaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMangaViewModel @Inject constructor(
    private val mangaUseCase: MangaUseCase
) : ViewModel() {
    fun insertFavoriteManga(manga: Manga) {
        viewModelScope.launch {
            mangaUseCase.insertFavoriteManga(manga)
        }
    }

    fun deleteFavoriteManga(malId: Int) {
        viewModelScope.launch {
            mangaUseCase.deleteFavoriteManga(malId)
        }
    }
}