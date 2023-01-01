package com.gosty.myotakulist.core.data.source.remote.response.common

import com.google.gson.annotations.SerializedName

data class CommonResponse<T>(
    @field:SerializedName("data")
    val data: T
)
