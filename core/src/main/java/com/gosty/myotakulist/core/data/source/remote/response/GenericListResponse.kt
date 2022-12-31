package com.gosty.myotakulist.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GenericListResponse<T>(
    @field:SerializedName("data")
    val data: List<T>
)
