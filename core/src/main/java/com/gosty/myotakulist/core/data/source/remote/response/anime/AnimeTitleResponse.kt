package com.gosty.myotakulist.core.data.source.remote.response.anime

import com.google.gson.annotations.SerializedName

data class AnimeTitleResponse(
    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("title")
    val title: String
)
