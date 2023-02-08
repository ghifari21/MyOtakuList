package com.gosty.myotakulist.core.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.gosty.myotakulist.core.data.source.local.LocalDataSource
import com.gosty.myotakulist.core.data.source.remote.RemoteDataSource
import com.gosty.myotakulist.core.domain.model.anime.Anime
import com.gosty.myotakulist.core.domain.repository.AnimeRepository
import com.gosty.myotakulist.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : AnimeRepository {
    override fun getSeasonsAnime(): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
                remoteDataSource.getSeasonsAnime()
            }
        ).flow
            .map { data ->
                data.map { response ->
                    DataMapper.mapAnimeResponseToAnimeDomain(response)
                }
            }
    }

    override fun getTopAnime(): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
                remoteDataSource.getTopAnime()
            }
        ).flow
            .map { data ->
                data.map { response ->
                    DataMapper.mapAnimeResponseToAnimeDomain(response)
                }
            }
    }

    override fun getSearchAnime(query: String): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
                remoteDataSource.getSearchAnime(query)
            }
        ).flow
            .map { data ->
                data.map { response ->
                    DataMapper.mapAnimeResponseToAnimeDomain(response)
                }
            }
    }

    override fun getAllFavoriteAnime(): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
                localDataSource.getAllFavoriteAnime()
            }
        ).flow
            .map {
                it.map { entity ->
                    DataMapper.mapAnimeEntityToAnimeDomain(entity)
                }
            }
    }

    override suspend fun insertFavoriteAnime(anime: Anime) {
        val animeEntity = DataMapper.mapAnimeDomainToAnimeEntity(anime)
        localDataSource.insertFavoriteAnime(animeEntity)
    }

    override suspend fun deleteFavoriteAnime(malId: Int) {
        localDataSource.deleteFavoriteAnime(malId)
    }
}