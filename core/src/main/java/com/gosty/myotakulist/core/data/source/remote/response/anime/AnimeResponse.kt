package com.gosty.myotakulist.core.data.source.remote.response.anime

import com.google.gson.annotations.SerializedName
import com.gosty.myotakulist.core.data.source.remote.response.common.GeneralResponse
import com.gosty.myotakulist.core.data.source.remote.response.common.ImageResponse
import com.gosty.myotakulist.core.data.source.remote.response.common.TitleResponse

data class AnimeResponse(
    @field:SerializedName("mal_id")
    val malId: Int,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("images")
    val images: ImageResponse,

    @field:SerializedName("trailer")
    val trailer: AnimeTrailerResponse,

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

    @field:SerializedName("source")
    val source: String? = null,

    @field:SerializedName("episodes")
    val episodes: Int? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("airing")
    val isAiring: Boolean,

    @field:SerializedName("aired")
    val aired: AnimeAiredResponse,

    @field:SerializedName("duration")
    val duration: String? = null,

    @field:SerializedName("rating")
    val rating: String? = null,

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

    @field:SerializedName("season")
    val season: String? = null,

    @field:SerializedName("year")
    val year: Int? = null,

    @field:SerializedName("broadcast")
    val broadcast: AnimeBroadcastResponse,

    @field:SerializedName("producers")
    val producers: List<GeneralResponse>,

    @field:SerializedName("licensors")
    val licensors: List<GeneralResponse>,

    @field:SerializedName("studios")
    val studios: List<GeneralResponse>,

    @field:SerializedName("genres")
    val genres: List<GeneralResponse>,

    @field:SerializedName("explicit_genres")
    val explicitGenres: List<GeneralResponse>,

    @field:SerializedName("themes")
    val themes: List<GeneralResponse>,

    @field:SerializedName("demographics")
    val demographics: List<GeneralResponse>
)