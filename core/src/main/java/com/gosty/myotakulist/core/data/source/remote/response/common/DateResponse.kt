package com.gosty.myotakulist.core.data.source.remote.response.common

import com.google.gson.annotations.SerializedName

data class DateResponse(
    @field:SerializedName("day")
    val day: Int? = null,

    @field:SerializedName("month")
    val month: Int? = null,

    @field:SerializedName("year")
    val year: Int? = null
)
