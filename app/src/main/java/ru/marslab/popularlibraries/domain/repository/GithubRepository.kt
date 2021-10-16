package ru.marslab.popularlibraries.domain.repository

import ru.marslab.popularlibraries.domain.model.GithubUser

interface GithubRepository {
    fun getUsers(): List<GithubUser>
}