package ru.marslab.popularlibraries.data.repository

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.marslab.popularlibraries.data.mapper.toDomain
import ru.marslab.popularlibraries.data.retrofit.GithubService
import ru.marslab.popularlibraries.domain.model.GithubRepo
import ru.marslab.popularlibraries.domain.model.GithubUser
import ru.marslab.popularlibraries.domain.repository.GithubRepository

private const val HTTP_LOG_TAG = "HTTP_LOG"

class GithubRepositoryImpl(
    private val githubService: GithubService
) : GithubRepository {

    override fun getUsers(): Single<List<GithubUser>> =
        githubService.getUsers()
            .map { resultList ->
                resultList.map { it.toDomain() }
            }
            .subscribeOn(Schedulers.io())

    override fun getUserRepos(user: GithubUser): Single<List<GithubRepo>> =
        githubService.getUserRepos(user.reposUrl)
            .map { list ->
                list.map { it.toDomain() }
            }
            .subscribeOn(Schedulers.io())
}
