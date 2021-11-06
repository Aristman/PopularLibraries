package ru.marslab.popularlibraries.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import ru.marslab.popularlibraries.data.model.room.GithubRepoDB

@Dao
interface GithubRepoDao {
    @Query("SELECT * FROM github_user_repos WHERE id=:userId")
    fun getUserRepos(userId: Int): Single<List<GithubRepoDB>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveUserRepo(repo: GithubRepoDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRepos(repos: List<GithubRepoDB>)

    @Delete
    fun removeRepo(repo: GithubRepoDB)
}
