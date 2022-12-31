package com.gosty.myotakulist.core.data.source.remote.response.anime

import com.google.gson.annotations.SerializedName

data class AnimeAiredResponse(
    @field:SerializedName("from")
    val from: String? = null,

    @field:SerializedName("to")
    val to: String? = null,

    @field:SerializedName("prop")
    val prop: AnimePropResponse
)
