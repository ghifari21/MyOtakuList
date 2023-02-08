package com.gosty.myotakulist.core.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.gosty.myotakulist.core.data.source.local.LocalDataSource
import com.gosty.myotakulist.core.data.source.remote.RemoteDataSource
import com.gosty.myotakulist.core.domain.model.manga.Manga
import com.gosty.myotakulist.core.domain.repository.MangaRepository
import com.gosty.myotakulist.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MangaRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : MangaRepository {
    override fun getSearchManga(query: String): Flow<PagingData<Manga>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
                remoteDataSource.getSearchManga(query)
            }
        ).flow
            .map { data ->
                data.map { response ->
                    DataMapper.mapMangaResponseToMangaDomain(response)
                }
            }
    }

    override fun getTopManga(): Flow<PagingData<Manga>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
                remoteDataSource.getTopManga()
            }
        ).flow
            .map { data ->
                data.map { response ->
                    DataMapper.mapMangaResponseToMangaDomain(response)
                }
            }
    }

    override fun getAllFavoriteManga(): Flow<PagingData<Manga>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
                localDataSource.getAllFavoriteManga()
            }
        ).flow
            .map { data ->
                data.map { entity ->
                    DataMapper.mapMangaEntityToMangaDomain(entity)
                }
            }
    }

    override suspend fun insertFavoriteManga(manga: Manga) {
        val mangaEntity = DataMapper.mapMangaDomainToMangaEntity(manga)
        localDataSource.insertFavoriteManga(mangaEntity)
    }

    override suspend fun deleteFavoriteManga(malId: Int) {
        localDataSource.deleteFavoriteManga(malId)
    }
}