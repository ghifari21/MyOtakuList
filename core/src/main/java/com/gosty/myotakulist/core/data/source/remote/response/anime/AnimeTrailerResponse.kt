package com.gosty.myotakulist.core.data.source.remote.response.anime

import com.google.gson.annotations.SerializedName

data class AnimeTrailerResponse(
    @field:SerializedName("youtube_id")
    val youtubeId: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("embed_url")
    val embedUrl: String? = null
)
