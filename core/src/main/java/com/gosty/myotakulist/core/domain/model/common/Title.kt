package com.gosty.myotakulist.core.domain.model.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Title(
    val type: String,
    val title: String
) : Parcelable
