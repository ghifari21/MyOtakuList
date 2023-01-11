package com.gosty.myotakulist.core.domain.usecase

import com.gosty.myotakulist.core.domain.repository.MangaRepository
import javax.inject.Inject

class MangaInteractor @Inject constructor(
    private val mangaRepository: MangaRepository
) : MangaUseCase {
}