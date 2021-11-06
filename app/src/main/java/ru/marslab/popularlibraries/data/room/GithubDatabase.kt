package ru.marslab.popularlibraries.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.marslab.popularlibraries.data.model.room.GithubUserReposDB
import ru.marslab.popularlibraries.data.model.room.GithubUsersDB

@Database(entities = [GithubUsersDB::class, GithubUserReposDB::class], version = 1)
abstract class GithubDatabase : RoomDatabase() {
    abstract fun GithubDao(): GithubDao
}