package ru.marslab.popularlibraries.data.repository

import android.util.Log
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.marslab.popularlibraries.data.mapper.toDB
import ru.marslab.popularlibraries.data.mapper.toDomain
import ru.marslab.popularlibraries.data.retrofit.GithubService
import ru.marslab.popularlibraries.data.room.GithubDatabase
import ru.marslab.popularlibraries.domain.model.GithubRepo
import ru.marslab.popularlibraries.domain.model.GithubUser
import ru.marslab.popularlibraries.domain.repository.GithubRepository
import ru.marslab.popularlibraries.domain.util.INetworkStatus

class GithubRepositoryWithCacheImpl(
    private val apiService: GithubService,
    private val githubDB: GithubDatabase,
    private val networkStatus: INetworkStatus
) : GithubRepository {

    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle()
            .flatMap { isOnline ->
                if (isOnline) {
                    apiService.getUsers()
                        .flatMap { users ->
                            Single.fromCallable {
                                githubDB.userDao().saveUsers(users.map { it.toDB() })
                                users.map { it.toDomain() }
                            }
                        }
                } else {
                    githubDB.userDao().getAllUsers()
                        .map { usersDb ->
                            usersDb.map { it.toDomain() }
                        }
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
                                githubDB.repoDao().saveRepos(repos.map { it.toDB(user.id) })
                                repos.map { it.toDomain() }
                            }
                                .doOnError { Log.d("MY_LOG", it.message.orEmpty()) }
                        }
                } else {
                    githubDB.repoDao().getUserRepos(user.id)
                        .map { reposDb ->
                            reposDb.map { it.toDomain() }
                        }
                }
            }
            .subscribeOn(Schedulers.io())
}
