package com.gosty.myotakulist.core.data.source.remote.response.anime

import com.google.gson.annotations.SerializedName

data class AnimeBroadcastResponse(
    @field:SerializedName("day")
    val day: String? = null,

    @field:SerializedName("time")
    val time: String? = null,

    @field:SerializedName("timezone")
    val timezone: String? = null,

    @field:SerializedName("string")
    val string: String? = null
)
