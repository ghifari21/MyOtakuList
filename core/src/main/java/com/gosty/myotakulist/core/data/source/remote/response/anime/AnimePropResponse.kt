package com.gosty.myotakulist.core.data.source.remote.response.anime

import com.google.gson.annotations.SerializedName

data class AnimePropResponse(
    @field:SerializedName("from")
    val from: AnimeDateResponse,

    @field:SerializedName("to")
    val to: AnimeDateResponse,

    @field:SerializedName("string")
    val string: String? = null
)
