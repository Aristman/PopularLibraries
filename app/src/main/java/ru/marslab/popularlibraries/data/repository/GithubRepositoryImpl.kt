package ru.marslab.popularlibraries.data.repository

import io.reactivex.Observable
import ru.marslab.popularlibraries.domain.model.GithubUser
import ru.marslab.popularlibraries.domain.repository.GithubRepository

class GithubRepositoryImpl : GithubRepository {
    override fun getUsers(): Observable<List<GithubUser>> {
        TODO("Not yet implemented")
    }
}