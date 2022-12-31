package com.gosty.myotakulist.core.data.source.remote.response.anime

import com.google.gson.annotations.SerializedName

data class AnimeImageResponse(
    @field:SerializedName("jpg")
    val jpg: AnimeImageTypeResponse,

    @field:SerializedName("webp")
    val webp: AnimeImageTypeResponse
)
