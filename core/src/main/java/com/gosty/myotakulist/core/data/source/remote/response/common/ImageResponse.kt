package com.gosty.myotakulist.core.data.source.remote.response.common

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @field:SerializedName("jpg")
    val jpg: ImageTypeResponse,

    @field:SerializedName("webp")
    val webp: ImageTypeResponse
)
