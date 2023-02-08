package com.gosty.myotakulist.manga.top

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gosty.myotakulist.core.domain.model.manga.Manga
import com.gosty.myotakulist.core.domain.usecase.MangaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopMangaViewModel @Inject constructor(
    private val mangaUseCase: MangaUseCase
) : ViewModel() {
    fun getTopManga(): LiveData<PagingData<Manga>> =
        mangaUseCase
            .getTopManga()
            .cachedIn(viewModelScope)
            .asLiveData()
}