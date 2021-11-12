package ru.marslab.popularlibraries.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.marslab.popularlibraries.data.model.room.GithubRepoDB
import ru.marslab.popularlibraries.data.model.room.GithubUserDB

@Database(entities = [GithubUserDB::class, GithubRepoDB::class], version = 1)
abstract class GithubDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME = "database.db"
        private var instance: GithubDatabase? = null
        fun getInstance() = instance
            ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context?) {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context!!, GithubDatabase::class.java,
                    DB_NAME
                )
                    .build()
            }
        }
    }

    abstract fun userDao(): GithubUserDao
    abstract fun repoDao(): GithubRepoDao
}
