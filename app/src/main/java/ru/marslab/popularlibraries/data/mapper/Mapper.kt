package ru.marslab.popularlibraries.data.mapper

import ru.marslab.popularlibraries.data.model.GithubUserNW
import ru.marslab.popularlibraries.domain.model.GithubUser

fun GithubUserNW.toDomain(): GithubUser =
    GithubUser(
        id = id,
        login = login,
        avatar = avatarUrl
    )