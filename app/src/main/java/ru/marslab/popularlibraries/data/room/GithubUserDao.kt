package ru.marslab.popularlibraries.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.Single
import ru.marslab.popularlibraries.data.model.room.GithubUsersDB

@Dao
interface GithubUserDao {
    @Query("SELECT * FROM github_users")
    fun getAllUsers(): Single<List<GithubUsersDB>>

    @Insert(onConflict = IGNORE)
    fun saveUser(user: GithubUsersDB)

    @Insert(onConflict = REPLACE)
    fun saveUsers(users: List<GithubUsersDB>)

    @Delete
    fun removeUser(user: GithubUsersDB)
}
