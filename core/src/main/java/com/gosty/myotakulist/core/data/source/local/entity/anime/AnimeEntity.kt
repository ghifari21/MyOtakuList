package com.gosty.myotakulist.core.data.source.local.entity.anime

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gosty.myotakulist.core.data.source.local.entity.common.GeneralEntity
import com.gosty.myotakulist.core.data.source.local.entity.common.TitleEntity

@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey(autoGenerate = false)
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

    @ColumnInfo(name = "source")
    var source: String? = null,

    @ColumnInfo(name = "episodes")
    var episodes: Int? = null,

    @ColumnInfo(name = "status")
    var status: String? = null,

    @ColumnInfo(name = "isAiring")
    var isAiring: Boolean,

    @ColumnInfo(name = "airedFrom")
    var airedFrom: String? = null,

    @ColumnInfo(name = "airedTo")
    var airedTo: String? = null,

    @ColumnInfo(name = "airedProp")
    var airedProp: String? = null,

    @ColumnInfo(name = "duration")
    var duration: String? = null,

    @ColumnInfo(name = "rating")
    var rating: String? = null,

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

    @ColumnInfo(name = "season")
    var season: String? = null,

    @ColumnInfo(name = "year")
    var year: Int? = null,

    @ColumnInfo(name = "broadcastDay")
    var broadcastDay: String? = null,

    @ColumnInfo(name = "broadcastTime")
    var broadcastTime: String? = null,

    @ColumnInfo(name = "broadcastTimezone")
    var broadcastTimezone: String? = null,

    @ColumnInfo(name = "broadcastString")
    var broadcastString: String? = null,

    @ColumnInfo(name = "producers")
    var producers: List<GeneralEntity>,

    @ColumnInfo(name = "licensors")
    var licensors: List<GeneralEntity>,

    @ColumnInfo(name = "studios")
    var studios: List<GeneralEntity>,

    @ColumnInfo(name = "genres")
    var genres: List<GeneralEntity>,

    @ColumnInfo(name = "explicitGenres")
    var explicitGenres: List<GeneralEntity>,

    @ColumnInfo(name = "themes")
    var themes: List<GeneralEntity>,

    @ColumnInfo(name = "demographics")
    var demographics: List<GeneralEntity>,

    @ColumnInfo(name = "isFavorite", defaultValue = "true")
    var isFavorite: Boolean
)
