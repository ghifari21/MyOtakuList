package com.gosty.myotakulist.core.data.source.remote.response.common

import com.google.gson.annotations.SerializedName

data class GeneralResponse(
    @field:SerializedName("mal_id")
    val malId: Int,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("url")
    val url: String
)
