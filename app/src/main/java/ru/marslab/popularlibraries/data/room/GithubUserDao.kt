package ru.marslab.popularlibraries.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.Single
import ru.marslab.popularlibraries.data.model.room.GithubUserDB

@Dao
interface GithubUserDao {
    @Query("SELECT * FROM github_users")
    fun getAllUsers(): Single<List<GithubUserDB>>

    @Insert(onConflict = IGNORE)
    fun saveUser(user: GithubUserDB)

    @Insert(onConflict = REPLACE)
    fun saveUsers(users: List<GithubUserDB>)

    @Delete
    fun removeUser(user: GithubUserDB)
}
