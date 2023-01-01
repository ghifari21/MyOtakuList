package com.gosty.myotakulist.core.data.source.remote.response.anime

import com.google.gson.annotations.SerializedName
import com.gosty.myotakulist.core.data.source.remote.response.common.PropResponse

data class AnimeAiredResponse(
    @field:SerializedName("from")
    val from: String? = null,

    @field:SerializedName("to")
    val to: String? = null,

    @field:SerializedName("prop")
    val prop: PropResponse
)
