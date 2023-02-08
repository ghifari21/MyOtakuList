package com.gosty.myotakulist.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gosty.myotakulist.core.domain.usecase.AnimeUseCase
import com.gosty.myotakulist.core.domain.usecase.MangaUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val animeUseCase: AnimeUseCase,
    private val mangaUseCase: MangaUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(TabsViewModel::class.java) -> {
                TabsViewModel(animeUseCase, mangaUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}