package com.gosty.myotakulist.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GenericPaginationResponse<T>(
    @field:SerializedName("data")
    val data: List<T>,

    @field:SerializedName("pagination")
    val pagination: PaginationResponse
)
