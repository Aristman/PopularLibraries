package ru.marslab.popularlibraries.data.mapper

import ru.marslab.popularlibraries.data.model.GithubRepoNW
import ru.marslab.popularlibraries.data.model.GithubUserNW
import ru.marslab.popularlibraries.domain.model.GithubRepo
import ru.marslab.popularlibraries.domain.model.GithubUser

fun GithubUserNW.toDomain(): GithubUser =
    GithubUser(
        id = id,
        login = login,
        avatar = avatarUrl,
        reposUrl = reposUrl
    )

fun GithubRepoNW.toDomain(): GithubRepo =
    GithubRepo(
        id = id,
        name = name,
        private = private,
        forks = forks,
        description = description
    )
