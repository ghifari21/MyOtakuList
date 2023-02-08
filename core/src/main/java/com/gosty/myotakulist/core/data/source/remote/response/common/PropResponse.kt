package com.gosty.myotakulist.core.data.source.remote.response.common

import com.google.gson.annotations.SerializedName

data class PropResponse(
    @field:SerializedName("from")
    val from: DateResponse,

    @field:SerializedName("to")
    val to: DateResponse
)
