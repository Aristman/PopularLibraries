package ru.marslab.popularlibraries.domain.repository

import io.reactivex.Observable
import ru.marslab.popularlibraries.domain.model.GithubUser

interface GithubRepository {
    fun getUsers(): Observable<List<GithubUser>>
}