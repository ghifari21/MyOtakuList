package com.gosty.myotakulist.core.data.source.remote.response.common

import com.google.gson.annotations.SerializedName

data class ImageTypeResponse(
    @field:SerializedName("image_url")
    val imageUrl: String? = null,

    @field:SerializedName("small_image_url")
    val smallImageUrl: String? = null,

    @field:SerializedName("large_image_url")
    val largeImageUrl: String? = null
)
