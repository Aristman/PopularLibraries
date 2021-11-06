package ru.marslab.popularlibraries.data.mapper

import ru.marslab.popularlibraries.data.model.GithubRepoNW
import ru.marslab.popularlibraries.data.model.GithubUserNW
import ru.marslab.popularlibraries.data.model.room.GithubRepoDB
import ru.marslab.popularlibraries.data.model.room.GithubUsersDB
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
        userId = owner.id,
        name = name,
        private = private,
        forks = forks,
        description = description
    )

fun GithubUserNW.toDB(): GithubUsersDB =
    GithubUsersDB(
        id = id,
        login = login,
        avatar = avatarUrl,
        reposUrl = reposUrl
    )

fun GithubRepoNW.toDB(userId: Int): GithubRepoDB =
    GithubRepoDB(
        id = id,
        userId = userId,
        name = name,
        isPrivate = private,
        forks = forks,
        description = description ?: ""
    )

fun GithubRepoDB.toDomain(): GithubRepo =
    GithubRepo(id, userId, name, isPrivate, forks, description)

fun GithubUsersDB.toDomain(): GithubUser =
    GithubUser(id, login, avatar, reposUrl)