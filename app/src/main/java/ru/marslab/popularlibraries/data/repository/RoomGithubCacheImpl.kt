package ru.marslab.popularlibraries.data.repository

import io.reactivex.Single
import ru.marslab.popularlibraries.data.mapper.toDB
import ru.marslab.popularlibraries.data.mapper.toDomain
import ru.marslab.popularlibraries.data.room.GithubDatabase
import ru.marslab.popularlibraries.domain.model.GithubRepo
import ru.marslab.popularlibraries.domain.model.GithubUser
import ru.marslab.popularlibraries.domain.util.IRoomGithubCache

class RoomGithubCacheImpl(
    private val githubDB: GithubDatabase
) : IRoomGithubCache {
    override fun cacheUsers(users: List<GithubUser>) {
        githubDB.userDao()
            .saveUsers(users.map { it.toDB() })
    }

    override fun cacheRepos(repos: List<GithubRepo>) {
        githubDB.repoDao()
            .saveRepos(repos.map { it.toDB() })
    }

    override fun getCachedUsers(): Single<List<GithubUser>> =
        githubDB.userDao().getAllUsers()
            .map { users ->
                users.map { it.toDomain() }
            }

    override fun getCachedRepos(userId: Int): Single<List<GithubRepo>> =
        githubDB.repoDao().getUserRepos(userId)
            .map { repos ->
                repos.map { it.toDomain() }
            }
}