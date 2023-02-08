package com.gosty.myotakulist.core.domain.model.anime

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnimeBroadcast(
    val day: String? = null,
    val time: String? = null,
    val timezone: String? = null,
    val string: String? = null
) : Parcelable
