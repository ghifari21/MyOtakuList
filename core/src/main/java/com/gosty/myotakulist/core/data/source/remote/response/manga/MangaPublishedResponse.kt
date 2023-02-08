package com.gosty.myotakulist.core.data.source.remote.response.manga

import com.google.gson.annotations.SerializedName
import com.gosty.myotakulist.core.data.source.remote.response.common.PropResponse

data class MangaPublishedResponse(
    @field:SerializedName("from")
    val from: String? = null,

    @field:SerializedName("to")
    val to: String? = null,

    @field:SerializedName("prop")
    val prop: PropResponse,

    @field:SerializedName("string")
    val string: String? = null
)
