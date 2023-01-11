package com.gosty.myotakulist.core.domain.usecase

import com.gosty.myotakulist.core.domain.repository.AnimeRepository
import javax.inject.Inject

class AnimeInteractor @Inject constructor(
    private val animeRepository: AnimeRepository
) : AnimeUseCase {
}