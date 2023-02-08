package com.gosty.myotakulist.core.utils

import com.gosty.myotakulist.core.data.source.local.entity.anime.AnimeEntity
import com.gosty.myotakulist.core.data.source.local.entity.common.GeneralEntity
import com.gosty.myotakulist.core.data.source.local.entity.common.TitleEntity
import com.gosty.myotakulist.core.data.source.local.entity.manga.MangaEntity
import com.gosty.myotakulist.core.data.source.remote.response.anime.AnimeResponse
import com.gosty.myotakulist.core.data.source.remote.response.manga.MangaResponse
import com.gosty.myotakulist.core.domain.model.anime.Anime
import com.gosty.myotakulist.core.domain.model.anime.AnimeAired
import com.gosty.myotakulist.core.domain.model.anime.AnimeBroadcast
import com.gosty.myotakulist.core.domain.model.common.GeneralModel
import com.gosty.myotakulist.core.domain.model.common.Title
import com.gosty.myotakulist.core.domain.model.manga.Manga
import com.gosty.myotakulist.core.domain.model.manga.MangaPublished

object DataMapper {
    fun mapAnimeResponseToAnimeDomain(input: AnimeResponse): Anime {
        var jpg: String? = null
        if (input.images.jpg.smallImageUrl != null) jpg = input.images.jpg.smallImageUrl
        if (input.images.jpg.imageUrl != null) jpg = input.images.jpg.imageUrl
        if (input.images.jpg.largeImageUrl != null) jpg = input.images.jpg.largeImageUrl

        var webp: String? = null
        if (input.images.webp.smallImageUrl != null) webp = input.images.webp.smallImageUrl
        if (input.images.webp.imageUrl != null) webp = input.images.webp.imageUrl
        if (input.images.webp.largeImageUrl != null) webp = input.images.webp.largeImageUrl

        val anime = Anime(
            malId = input.malId,
            url = input.url,
            imageJpeg = jpg,
            imageWebp = webp,
            isApproved = input.isApproved,
            titles = input.titles.map {
                Title(
                    type = it.type,
                    title = it.title
                )
            },
            title = input.title,
            englishTitle = input.englishTitle,
            japaneseTitle = input.japaneseTitle,
            synonymsTitle = input.synonymsTitle,
            type = input.type,
            source = input.source,
            episodes = input.episodes,
            status = input.status,
            isAiring = input.isAiring,
            aired = AnimeAired(
                from = input.aired.from,
                to = input.aired.to,
                prop = input.aired.string
            ),
            duration = input.duration,
            rating = input.rating,
            score = input.score,
            scoredBy = input.scoredBy,
            rank = input.rank,
            popularity = input.popularity,
            members = input.members,
            favorites = input.favorites,
            synopsis = input.synopsis,
            background = input.background,
            season = input.season,
            year = input.year,
            broadcast = AnimeBroadcast(
                day = input.broadcast.day,
                time = input.broadcast.time,
                timezone = input.broadcast.timezone,
                string = input.broadcast.string
            ),
            producers = input.producers.map {
                GeneralModel(
                    malId = it.malId,
                    type = it.type,
                    name = it.name,
                    url = it.url
                )
            },
            licensors = input.licensors.map {
                GeneralModel(
                    malId = it.malId,
                    type = it.type,
                    name = it.name,
                    url = it.url
                )
            },
            studios = input.studios.map {
                GeneralModel(
                    malId = it.malId,
                    type = it.type,
                    name = it.name,
                    url = it.url
                )
            },
            genres = input.genres.map {
                GeneralModel(
                    malId = it.malId,
                    type = it.type,
                    name = it.name,
                    url = it.url
                )
            },
            explicitGenres = input.explicitGenres.map {
                GeneralModel(
                    malId = it.malId,
                    type = it.type,
                    name = it.name,
                    url = it.url
                )
            },
            themes = input.themes.map {
                GeneralModel(
                    malId = it.malId,
                    type = it.type,
                    name = it.name,
                    url = it.url
                )
            },
            demographics = input.demographics.map {
                GeneralModel(
                    malId = it.malId,
                    type = it.type,
                    name = it.name,
                    url = it.url
                )
            },
            isFavorite = false
        )

        return anime
    }

    fun mapAnimeEntityToAnimeDomain(input: AnimeEntity): Anime =
        Anime(
            malId = input.malId,
            url = input.url,
            imageJpeg = input.imageJpeg,
            imageWebp = input.imageWebp,
            isApproved = input.isApproved,
            titles = input.titles.map { data ->
                Title(
                    type = data.type,
                    title = data.title
                )
            },
            title = input.title,
            englishTitle = input.englishTitle,
            japaneseTitle = input.japaneseTitle,
            synonymsTitle = input.synonymsTitle,
            type = input.type,
            source = input.source,
            episodes = input.episodes,
            status = input.status,
            isAiring = input.isAiring,
            aired = AnimeAired(
                from = input.airedFrom,
                to = input.airedTo,
                prop = input.airedProp
            ),
            duration = input.duration,
            rating = input.rating,
            score = input.score,
            scoredBy = input.scoredBy,
            rank = input.rank,
            popularity = input.popularity,
            members = input.members,
            favorites = input.favorites,
            synopsis = input.synopsis,
            background = input.background,
            season = input.season,
            year = input.year,
            broadcast = AnimeBroadcast(
                day = input.broadcastDay,
                time = input.broadcastTime,
                timezone = input.broadcastTimezone,
                string = input.broadcastString
            ),
            producers = input.producers.map { data ->
                GeneralModel(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            licensors = input.licensors.map { data ->
                GeneralModel(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            studios = input.studios.map { data ->
                GeneralModel(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            genres = input.genres.map { data ->
                GeneralModel(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            explicitGenres = input.explicitGenres.map { data ->
                GeneralModel(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            themes = input.themes.map { data ->
                GeneralModel(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            demographics = input.demographics.map { data ->
                GeneralModel(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            isFavorite = input.isFavorite
        )

    fun mapAnimeDomainToAnimeEntity(input: Anime): AnimeEntity =
        AnimeEntity(
            malId = input.malId,
            url = input.url,
            imageJpeg = input.imageJpeg,
            imageWebp = input.imageWebp,
            isApproved = input.isApproved,
            titles = input.titles.map { data ->
                TitleEntity(
                    type = data.type,
                    title = data.title
                )
            },
            title = input.title,
            englishTitle = input.englishTitle,
            japaneseTitle = input.japaneseTitle,
            synonymsTitle = input.synonymsTitle,
            type = input.type,
            source = input.source,
            episodes = input.episodes,
            status = input.status,
            isAiring = input.isAiring,
            airedFrom = input.aired.from,
            airedTo = input.aired.to,
            airedProp = input.aired.prop,
            duration = input.duration,
            rating = input.rating,
            score = input.score,
            scoredBy = input.scoredBy,
            rank = input.rank,
            popularity = input.popularity,
            members = input.members,
            favorites = input.favorites,
            synopsis = input.synopsis,
            background = input.background,
            season = input.season,
            year = input.year,
            broadcastDay = input.broadcast.day,
            broadcastTime = input.broadcast.time,
            broadcastTimezone = input.broadcast.timezone,
            broadcastString = input.broadcast.string,
            producers = input.producers.map { data ->
                GeneralEntity(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            licensors = input.licensors.map { data ->
                GeneralEntity(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            studios = input.studios.map { data ->
                GeneralEntity(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            genres = input.genres.map { data ->
                GeneralEntity(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            explicitGenres = input.explicitGenres.map { data ->
                GeneralEntity(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            themes = input.themes.map { data ->
                GeneralEntity(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            demographics = input.demographics.map { data ->
                GeneralEntity(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            isFavorite = !input.isFavorite
        )

    fun mapMangaResponseToMangaDomain(input: MangaResponse): Manga {
        var jpg: String? = null
        if (input.images.jpg.smallImageUrl != null) jpg = input.images.jpg.smallImageUrl
        if (input.images.jpg.imageUrl != null) jpg = input.images.jpg.imageUrl
        if (input.images.jpg.largeImageUrl != null) jpg = input.images.jpg.largeImageUrl

        var webp: String? = null
        if (input.images.webp.smallImageUrl != null) webp = input.images.webp.smallImageUrl
        if (input.images.webp.imageUrl != null) webp = input.images.webp.imageUrl
        if (input.images.webp.largeImageUrl != null) webp = input.images.webp.largeImageUrl

        val manga = Manga(
            malId = input.malId,
            url = input.url,
            imageJpeg = jpg,
            imageWebp = webp,
            isApproved = input.isApproved,
            titles = input.titles.map {
                Title(
                    type = it.type,
                    title = it.title
                )
            },
            title = input.title,
            englishTitle = input.englishTitle,
            japaneseTitle = input.japaneseTitle,
            synonymsTitle = input.synonymsTitle,
            type = input.type,
            chapters = input.chapters,
            volumes = input.volumes,
            status = input.status,
            isPublishing = input.isPublishing,
            published = MangaPublished(
                from = input.published.from,
                to = input.published.to,
                prop = input.published.string
            ),
            score = input.score,
            scoredBy = input.scoredBy,
            rank = input.rank,
            popularity = input.popularity,
            members = input.members,
            favorites = input.favorites,
            synopsis = input.synopsis,
            background = input.background,
            authors = input.authors.map {
                GeneralModel(
                    malId = it.malId,
                    type = it.type,
                    name = it.name,
                    url = it.url
                )
            },
            serializations = input.serializations.map {
                GeneralModel(
                    malId = it.malId,
                    type = it.type,
                    name = it.name,
                    url = it.url
                )
            },
            genres = input.genres.map {
                GeneralModel(
                    malId = it.malId,
                    type = it.type,
                    name = it.name,
                    url = it.url
                )
            },
            explicitGenres = input.explicitGenres.map {
                GeneralModel(
                    malId = it.malId,
                    type = it.type,
                    name = it.name,
                    url = it.url
                )
            },
            themes = input.themes.map {
                GeneralModel(
                    malId = it.malId,
                    type = it.type,
                    name = it.name,
                    url = it.url
                )
            },
            demographics = input.demographics.map {
                GeneralModel(
                    malId = it.malId,
                    type = it.type,
                    name = it.name,
                    url = it.url
                )
            },
            isFavorite = false
        )

        return manga
    }

    fun mapMangaEntityToMangaDomain(input: MangaEntity): Manga =
        Manga(
            malId = input.malId,
            url = input.url,
            imageJpeg = input.imageJpeg,
            imageWebp = input.imageWebp,
            isApproved = input.isApproved,
            titles = input.titles.map { data ->
                Title(
                    type = data.type,
                    title = data.title
                )
            },
            title = input.title,
            englishTitle = input.englishTitle,
            japaneseTitle = input.japaneseTitle,
            synonymsTitle = input.synonymsTitle,
            type = input.type,
            chapters = input.chapters,
            volumes = input.volumes,
            status = input.status,
            isPublishing = input.isPublishing,
            published = MangaPublished(
                from = input.publishedFrom,
                to = input.publishedTo,
                prop = input.publishedProp
            ),
            score = input.score,
            scoredBy = input.scoredBy,
            rank = input.rank,
            popularity = input.popularity,
            members = input.members,
            favorites = input.favorites,
            synopsis = input.synopsis,
            background = input.background,
            authors = input.authors.map { data ->
                GeneralModel(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            serializations = input.serializations.map { data ->
                GeneralModel(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            genres = input.genres.map { data ->
                GeneralModel(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            explicitGenres = input.explicitGenres.map { data ->
                GeneralModel(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            themes = input.themes.map { data ->
                GeneralModel(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            demographics = input.demographics.map { data ->
                GeneralModel(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            isFavorite = input.isFavorite
        )

    fun mapMangaDomainToMangaEntity(input: Manga): MangaEntity =
        MangaEntity(
            malId = input.malId,
            url = input.url,
            imageJpeg = input.imageJpeg,
            imageWebp = input.imageWebp,
            isApproved = input.isApproved,
            titles = input.titles.map { data ->
                TitleEntity(
                    type = data.type,
                    title = data.title
                )
            },
            title = input.title,
            englishTitle = input.englishTitle,
            japaneseTitle = input.japaneseTitle,
            synonymsTitle = input.synonymsTitle,
            type = input.type,
            chapters = input.chapters,
            volumes = input.volumes,
            status = input.status,
            isPublishing = input.isPublishing,
            publishedFrom = input.published.from,
            publishedTo = input.published.to,
            publishedProp = input.published.prop,
            score = input.score,
            scoredBy = input.scoredBy,
            rank = input.rank,
            popularity = input.popularity,
            members = input.members,
            favorites = input.favorites,
            synopsis = input.synopsis,
            background = input.background,
            authors = input.authors.map { data ->
                GeneralEntity(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            serializations = input.serializations.map { data ->
                GeneralEntity(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            genres = input.genres.map { data ->
                GeneralEntity(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            explicitGenres = input.explicitGenres.map { data ->
                GeneralEntity(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            themes = input.themes.map { data ->
                GeneralEntity(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            demographics = input.demographics.map { data ->
                GeneralEntity(
                    malId = data.malId,
                    type = data.type,
                    name = data.name,
                    url = data.url
                )
            },
            isFavorite = !input.isFavorite
        )
}