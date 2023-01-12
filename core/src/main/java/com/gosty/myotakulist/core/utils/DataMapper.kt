package com.gosty.myotakulist.core.utils

import com.gosty.myotakulist.core.data.source.local.entity.anime.AnimeEntity
import com.gosty.myotakulist.core.data.source.local.entity.common.GeneralEntity
import com.gosty.myotakulist.core.data.source.local.entity.common.TitleEntity
import com.gosty.myotakulist.core.data.source.remote.response.anime.AnimeResponse
import com.gosty.myotakulist.core.domain.model.anime.Anime
import com.gosty.myotakulist.core.domain.model.anime.AnimeAired
import com.gosty.myotakulist.core.domain.model.anime.AnimeBroadcast
import com.gosty.myotakulist.core.domain.model.common.GeneralModel
import com.gosty.myotakulist.core.domain.model.common.Title

object DataMapper {
    fun mapAnimeResponsesToAnimeEntities(input: List<AnimeResponse>): List<AnimeEntity> {
        val animeList = ArrayList<AnimeEntity>()
        input.map {
            var jpg: String? = null
            if (it.images.jpg.smallImageUrl != null) jpg = it.images.jpg.smallImageUrl
            if (it.images.jpg.imageUrl != null) jpg = it.images.jpg.imageUrl
            if (it.images.jpg.largeImageUrl != null) jpg = it.images.jpg.largeImageUrl

            var webp: String? = null
            if (it.images.webp.smallImageUrl != null) webp = it.images.webp.smallImageUrl
            if (it.images.webp.imageUrl != null) webp = it.images.webp.imageUrl
            if (it.images.webp.largeImageUrl != null) webp = it.images.webp.largeImageUrl

            val anime = AnimeEntity(
                malId = it.malId,
                url = it.url,
                imageJpeg = jpg,
                imageWebp = webp,
                isApproved = it.isApproved,
                titles = it.titles.map { data ->
                    TitleEntity(
                        type = data.type,
                        title = data.title
                    )
                },
                title = it.title,
                englishTitle = it.englishTitle,
                japaneseTitle = it.japaneseTitle,
                synonymsTitle = it.synonymsTitle,
                type = it.type,
                episodes = it.episodes,
                status = it.status,
                isAiring = it.isAiring,
                airedFrom = it.aired.from,
                airedTo = it.aired.to,
                airedProp = it.aired.prop.string,
                duration = it.duration,
                rating = it.rating,
                score = it.score,
                scoredBy = it.scoredBy,
                rank = it.rank,
                popularity = it.popularity,
                members = it.members,
                favorites = it.favorites,
                synopsis = it.synopsis,
                background = it.background,
                year = it.year,
                broadcastDay = it.broadcast.day,
                broadcastTime = it.broadcast.time,
                broadcastTimezone = it.broadcast.timezone,
                broadcastString = it.broadcast.string,
                producers = it.producers.map { data ->
                    GeneralEntity(
                        malId = data.malId,
                        type = data.type,
                        name = data.name,
                        url = data.url
                    )
                },
                licensors = it.licensors.map { data ->
                    GeneralEntity(
                        malId = data.malId,
                        type = data.type,
                        name = data.name,
                        url = data.url
                    )
                },
                studios = it.studios.map { data ->
                    GeneralEntity(
                        malId = data.malId,
                        type = data.type,
                        name = data.name,
                        url = data.url
                    )
                },
                genres = it.genres.map { data ->
                    GeneralEntity(
                        malId = data.malId,
                        type = data.type,
                        name = data.name,
                        url = data.url
                    )
                },
                explicitGenres = it.genres.map { data ->
                    GeneralEntity(
                        malId = data.malId,
                        type = data.type,
                        name = data.name,
                        url = data.url
                    )
                },
                themes = it.themes.map { data ->
                    GeneralEntity(
                        malId = data.malId,
                        type = data.type,
                        name = data.name,
                        url = data.url
                    )
                },
                demographics = it.demographics.map { data ->
                    GeneralEntity(
                        malId = data.malId,
                        type = data.type,
                        name = data.name,
                        url = data.url
                    )
                },
                isFavorite = false
            )
            animeList.add(anime)
        }
        return animeList
    }

    fun mapAnimeEntitiesToAnimeDomain(input: List<AnimeEntity>): List<Anime> {
        val animeList = ArrayList<Anime>()
        input.map {
            val anime = Anime(
                malId = it.malId,
                url = it.url,
                imageJpeg = it.imageJpeg,
                imageWebp = it.imageWebp,
                isApproved = it.isApproved,
                titles = it.titles.map { data ->
                    Title(
                        type = data.type,
                        title = data.title
                    )
                },
                title = it.title,
                englishTitle = it.englishTitle,
                japaneseTitle = it.japaneseTitle,
                synonymsTitle = it.synonymsTitle,
                type = it.type,
                source = it.source,
                episodes = it.episodes,
                status = it.status,
                isAiring = it.isAiring,
                aired = AnimeAired(
                    from = it.airedFrom,
                    to = it.airedTo,
                    prop = it.airedProp
                ),
                duration = it.duration,
                rating = it.rating,
                score = it.score,
                scoredBy = it.scoredBy,
                rank = it.rank,
                popularity = it.popularity,
                members = it.members,
                favorites = it.favorites,
                synopsis = it.synopsis,
                background = it.background,
                year = it.year,
                broadcast = AnimeBroadcast(
                    day = it.broadcastDay,
                    time = it.broadcastTime,
                    timezone = it.broadcastTimezone,
                    string = it.broadcastString
                ),
                producers = it.producers.map { data ->
                    GeneralModel(
                        malId = data.malId,
                        type = data.type,
                        name = data.name,
                        url = data.url
                    )
                },
                licensors = it.licensors.map { data ->
                    GeneralModel(
                        malId = data.malId,
                        type = data.type,
                        name = data.name,
                        url = data.url
                    )
                },
                studios = it.studios.map { data ->
                    GeneralModel(
                        malId = data.malId,
                        type = data.type,
                        name = data.name,
                        url = data.url
                    )
                },
                genres = it.genres.map { data ->
                    GeneralModel(
                        malId = data.malId,
                        type = data.type,
                        name = data.name,
                        url = data.url
                    )
                },
                explicitGenres = it.explicitGenres.map { data ->
                    GeneralModel(
                        malId = data.malId,
                        type = data.type,
                        name = data.name,
                        url = data.url
                    )
                },
                themes = it.themes.map { data ->
                    GeneralModel(
                        malId = data.malId,
                        type = data.type,
                        name = data.name,
                        url = data.url
                    )
                },
                demographics = it.themes.map { data ->
                    GeneralModel(
                        malId = data.malId,
                        type = data.type,
                        name = data.name,
                        url = data.url
                    )
                },
                isFavorite = it.isFavorite
            )
            animeList.add(anime)
        }
        return animeList
    }

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
            rank = input.rank,
            popularity = input.popularity,
            members = input.members,
            synopsis = input.synopsis,
            background = input.background,
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
            isFavorite = input.isFavorite
        )
}