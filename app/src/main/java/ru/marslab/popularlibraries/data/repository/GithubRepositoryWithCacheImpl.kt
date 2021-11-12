package ru.marslab.popularlibraries.data.repository

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.marslab.popularlibraries.data.mapper.toDomain
import ru.marslab.popularlibraries.data.retrofit.GithubService
import ru.marslab.popularlibraries.domain.model.GithubRepo
import ru.marslab.popularlibraries.domain.model.GithubUser
import ru.marslab.popularlibraries.domain.repository.GithubRepository
import ru.marslab.popularlibraries.domain.util.INetworkStatus
import ru.marslab.popularlibraries.domain.util.IRoomGithubCache

class GithubRepositoryWithCacheImpl(
    private val apiService: GithubService,
    private val networkStatus: INetworkStatus,
    private val roomCache: IRoomGithubCache
) : GithubRepository {

    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle()
            .flatMap { isOnline ->
                if (isOnline) {
                    apiService.getUsers()
                        .flatMap { users ->
                            Single.fromCallable {
                                roomCache.cacheUsers(users.map { it.toDomain() })
                                users.map { it.toDomain() }
                            }
                        }
                } else {
                    roomCache.getCachedUsers()
                }
            }
            .subscribeOn(Schedulers.io())

    override fun getUserRepos(user: GithubUser): Single<List<GithubRepo>> =
        networkStatus.isOnlineSingle()
            .flatMap { isOnline ->
                if (isOnline) {
                    apiService.getUserRepos(user.reposUrl)
                        .flatMap { repos ->
                            Single.fromCallable {
                                roomCache.cacheRepos(repos.map { it.toDomain() })
                                repos.map { it.toDomain() }
                            }
                        }
                } else {
                    roomCache.getCachedRepos(user.id)
                }
            }
            .subscribeOn(Schedulers.io())
}
