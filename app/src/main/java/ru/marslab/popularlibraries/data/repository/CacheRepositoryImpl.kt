package ru.marslab.popularlibraries.data.repository

import ru.marslab.popularlibraries.data.room.GithubDatabase
import ru.marslab.popularlibraries.domain.repository.CacheRepository

class CacheRepositoryImpl(private val githubDB: GithubDatabase) : CacheRepository {

}