package ru.marslab.popularlibraries.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.marslab.popularlibraries.data.repository.GithubRepositoryWithCacheImpl
import ru.marslab.popularlibraries.data.repository.RoomGithubCacheImpl
import ru.marslab.popularlibraries.data.retrofit.GithubService
import ru.marslab.popularlibraries.data.room.GithubDatabase
import ru.marslab.popularlibraries.domain.repository.GithubRepository
import ru.marslab.popularlibraries.domain.util.INetworkStatus
import ru.marslab.popularlibraries.domain.util.IRoomGithubCache
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideGithubRepository(
        githubService: GithubService,
        networkStatus: INetworkStatus,
        roomCache: IRoomGithubCache
    ): GithubRepository =
        GithubRepositoryWithCacheImpl(githubService, networkStatus, roomCache)

    @Singleton
    @Provides
    fun provideRoomGithubCache(database: GithubDatabase): IRoomGithubCache =
        RoomGithubCacheImpl(database)

    @Singleton
    @Provides
    fun provideGithubDatabase(context: Context): GithubDatabase =
        Room.databaseBuilder(
            context, GithubDatabase::class.java,
            "database.db"
        )
            .build()
}