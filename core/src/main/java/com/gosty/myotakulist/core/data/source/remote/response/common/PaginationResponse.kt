package com.gosty.myotakulist.core.data.source.remote.response.common

import com.google.gson.annotations.SerializedName

data class PaginationResponse(
    @field:SerializedName("last_visible_page")
    val lastVisiblePage: Int,

    @field:SerializedName("has_next_page")
    val hasNextPage: Boolean,

    @field:SerializedName("items")
    val items: ItemResponse
)
