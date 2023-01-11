package com.gosty.myotakulist.core.data.source.remote.response.manga

import com.google.gson.annotations.SerializedName
import com.gosty.myotakulist.core.data.source.remote.response.common.GeneralResponse
import com.gosty.myotakulist.core.data.source.remote.response.common.ImageResponse
import com.gosty.myotakulist.core.data.source.remote.response.common.TitleResponse

data class MangaResponse(
    @field:SerializedName("mal_id")
    val malId: Int,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("images")
    val images: ImageResponse,

    @field:SerializedName("approved")
    val isApproved: Boolean,

    @field:SerializedName("titles")
    val titles: List<TitleResponse>,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("title_english")
    val englishTitle: String? = null,

    @field:SerializedName("title_japanese")
    val japaneseTitle: String? = null,

    @field:SerializedName("title_synonyms")
    val synonymsTitle: List<String>,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("chapters")
    val chapters: Int? = null,

    @field:SerializedName("volumes")
    val volumes: Int? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("publishing")
    val isPublishing: Boolean,

    @field:SerializedName("published")
    val published: MangaPublishedResponse,

    @field:SerializedName("score")
    val score: Double? = null,

    @field:SerializedName("scored_by")
    val scoredBy: Int? = null,

    @field:SerializedName("rank")
    val rank: Int? = null,

    @field:SerializedName("popularity")
    val popularity: Int? = null,

    @field:SerializedName("members")
    val members: Int? = null,

    @field:SerializedName("favorites")
    val favorites: Int? = null,

    @field:SerializedName("synopsis")
    val synopsis: String? = null,

    @field:SerializedName("background")
    val background: String? = null,

    @field:SerializedName("authors")
    val authors: List<GeneralResponse>,

    @field:SerializedName("serializations")
    val serializations: List<GeneralResponse>,

    @field:SerializedName("genres")
    val genres: List<GeneralResponse>,

    @field:SerializedName("explicit_genres")
    val explicitGenres: List<GeneralResponse>,

    @field:SerializedName("themes")
    val themes: List<GeneralResponse>,

    @field:SerializedName("demographics")
    val demographics: List<GeneralResponse>
)
