package com.gosty.myotakulist.core.domain.model.anime

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnimeAired(
    val from: String? = null,
    val to: String? = null,
    val prop: String? = null
) : Parcelable
