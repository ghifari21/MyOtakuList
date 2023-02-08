package com.gosty.myotakulist.core.data.source.remote.response.common

import com.google.gson.annotations.SerializedName

data class ItemResponse(
    @field:SerializedName("count")
    val count: Int,

    @field:SerializedName("total")
    val total: Int,

    @field:SerializedName("per_page")
    val perPage: Int
)
