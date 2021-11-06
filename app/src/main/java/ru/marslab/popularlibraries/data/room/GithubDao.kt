package ru.marslab.popularlibraries.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.Single
import ru.marslab.popularlibraries.data.model.room.GithubUserReposDB
import ru.marslab.popularlibraries.data.model.room.GithubUsersDB

@Dao
interface GithubDao {
    @Query("SELECT * FROM github_users")
    fun getAllUsers(): Single<List<GithubUsersDB>>

    @Query("SELECT * FROM github_user_repos WHERE id=:userId")
    fun getUserRepos(userId: Int): Single<List<GithubUserReposDB>>

    @Insert(onConflict = IGNORE)
    fun saveUser(user: GithubUsersDB)

    @Insert(onConflict = IGNORE)
    fun saveUserRepo(repo: GithubUserReposDB)

    @Insert(onConflict = REPLACE)
    fun saveUsers(users: List<GithubUsersDB>)

    @Insert(onConflict = REPLACE)
    fun saveRepos(repos: List<GithubUserReposDB>)

    @Delete
    fun removeUser(user: GithubUsersDB)

    @Delete
    fun removeRepo(repo: GithubUserReposDB)
}