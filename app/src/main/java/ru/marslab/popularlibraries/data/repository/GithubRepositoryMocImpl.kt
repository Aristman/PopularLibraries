package ru.marslab.popularlibraries.data.repository

import ru.marslab.popularlibraries.domain.model.GithubUser
import ru.marslab.popularlibraries.domain.repository.GithubRepository

class GithubRepositoryMocImpl : GithubRepository {
    private val users: List<GithubUser> =
        (0..55).map { GithubUser("User $it") }

    override fun getUsers(): List<GithubUser> =
        users
}