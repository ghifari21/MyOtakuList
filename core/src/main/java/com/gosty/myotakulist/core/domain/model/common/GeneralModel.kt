package com.gosty.myotakulist.core.domain.model.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GeneralModel(
    val malId: Int,
    val type: String,
    val name: String,
    val url: String
) : Parcelable
