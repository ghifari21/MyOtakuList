package com.gosty.myotakulist.core.data.source.local.entity.manga

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.gosty.myotakulist.core.data.source.local.entity.common.GeneralEntity
import com.gosty.myotakulist.core.data.source.local.entity.common.TitleEntity

@Entity(tableName = "manga")
data class MangaEntity(
    @ColumnInfo(name = "malId")
    var malId: Int,

    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "imageJpeg")
    var imageJpeg: String? = null,

    @ColumnInfo(name = "imageWebp")
    var imageWebp: String? = null,

    @ColumnInfo(name = "isApproved")
    var isApproved: Boolean,

    @ColumnInfo(name = "titles")
    var titles: List<TitleEntity>,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "englishTitle")
    var englishTitle: String? = null,

    @ColumnInfo(name = "japaneseTitle")
    var japaneseTitle: String? = null,

    @ColumnInfo(name = "synonymsTitle")
    var synonymsTitle: List<String>,

    @ColumnInfo(name = "type")
    var type: String? = null,

    @ColumnInfo(name = "chapters")
    var chapters: Int? = null,

    @ColumnInfo(name = "volumes")
    var volumes: Int? = null,

    @ColumnInfo(name = "status")
    var status: String? = null,

    @ColumnInfo(name = "isPublishing")
    var isPublishing: Boolean,

    @ColumnInfo(name = "publishedFrom")
    var publishedFrom: String? = null,

    @ColumnInfo(name = "publishedTo")
    var publishedTo: String? = null,

    @ColumnInfo(name = "publishedProp")
    var publishedProp: String? = null,

    @ColumnInfo(name = "score")
    var score: Double? = null,

    @ColumnInfo(name = "scoredBy")
    var scoredBy: Int? = null,

    @ColumnInfo(name = "rank")
    var rank: Int? = null,

    @ColumnInfo(name = "popularity")
    var popularity: Int? = null,

    @ColumnInfo(name = "members")
    var members: Int? = null,

    @ColumnInfo(name = "favorites")
    var favorites: Int? = null,

    @ColumnInfo(name = "synopsis")
    var synopsis: String? = null,

    @ColumnInfo(name = "background")
    var background: String? = null,

    @ColumnInfo(name = "authors")
    var authors: List<GeneralEntity>,

    @ColumnInfo(name = "serializations")
    var serializations: List<GeneralEntity>,

    @ColumnInfo(name = "genres")
    var genres: List<GeneralEntity>,

    @ColumnInfo(name = "explicitGenres")
    var explicitGenres: List<GeneralEntity>,

    @ColumnInfo(name = "themes")
    var themes: List<GeneralEntity>,

    @ColumnInfo(name = "demographics")
    var demographics: List<GeneralEntity>,

    @ColumnInfo(name = "isFavorite", defaultValue = "false")
    var isFavorite: Boolean
)
