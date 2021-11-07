package ru.marslab.popularlibraries.domain.util

import io.reactivex.Single
import ru.marslab.popularlibraries.domain.model.GithubRepo
import ru.marslab.popularlibraries.domain.model.GithubUser

interface IRoomGithubCache {
    fun cacheUsers(users: List<GithubUser>)
    fun cacheRepos(repos: List<GithubRepo>)
    fun getCachedUsers(): Single<List<GithubUser>>
    fun getCachedRepos(userId: Int): Single<List<GithubRepo>>
}