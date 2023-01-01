package com.gosty.myotakulist.core.data.source.remote.response.common

import com.google.gson.annotations.SerializedName

data class TitleResponse(
    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("title")
    val title: String
)
