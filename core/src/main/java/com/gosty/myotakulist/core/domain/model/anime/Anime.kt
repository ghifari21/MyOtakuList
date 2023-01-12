package com.gosty.myotakulist.core.domain.model.anime

import android.os.Parcelable
import com.gosty.myotakulist.core.domain.model.common.GeneralModel
import com.gosty.myotakulist.core.domain.model.common.Title
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime(
    val malId: Int,
    val url: String,
    val imageJpeg: String? = null,
    val imageWebp: String? = null,
    val isApproved: Boolean,
    val titles: List<Title>,
    val title: String,
    val englishTitle: String? = null,
    val japaneseTitle: String? = null,
    val synonymsTitle: List<String>,
    val type: String? = null,
    val source: String? = null,
    val episodes: Int? = null,
    val status: String? = null,
    val isAiring: Boolean,
    val aired: AnimeAired,
    val duration: String? = null,
    val rating: String? = null,
    val score: Double? = null,
    val scoredBy: Int? = null,
    val rank: Int? = null,
    val popularity: Int? = null,
    val members: Int? = null,
    val favorites: Int? = null,
    val synopsis: String? = null,
    val background: String? = null,
    val year: Int? = null,
    val broadcast: AnimeBroadcast,
    val producers: List<GeneralModel>,
    val licensors: List<GeneralModel>,
    val studios: List<GeneralModel>,
    val genres: List<GeneralModel>,
    val explicitGenres: List<GeneralModel>,
    val themes: List<GeneralModel>,
    val demographics: List<GeneralModel>,
    var isFavorite: Boolean
) : Parcelable
