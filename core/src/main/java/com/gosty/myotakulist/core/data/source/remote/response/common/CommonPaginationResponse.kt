package com.gosty.myotakulist.core.data.source.remote.response.common

import com.google.gson.annotations.SerializedName

data class CommonPaginationResponse<T>(
    @field:SerializedName("data")
    val data: List<T>,

    @field:SerializedName("pagination")
    val pagination: PaginationResponse
)
