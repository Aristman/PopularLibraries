package ru.marslab.popularlibraries.domain.repository

import io.reactivex.Single
import ru.marslab.popularlibraries.domain.model.GithubRepo
import ru.marslab.popularlibraries.domain.model.GithubUser

interface GithubRepository {
    fun getUsers(): Single<List<GithubUser>>
    fun getUserRepos(user: GithubUser): Single<List<GithubRepo>>
}
