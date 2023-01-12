package com.gosty.myotakulist.core.domain.model.manga

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MangaPublished(
    val from: String? = null,
    val to: String? = null,
    val prop: String? = null
) : Parcelable
