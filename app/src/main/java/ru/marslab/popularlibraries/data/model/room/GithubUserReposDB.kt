package ru.marslab.popularlibraries.data.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "github_user_repos")
data class GithubUserReposDB(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "user_id")
    val userId: Int,
    val name: String,
    val private: Boolean,
    val forks: Int,
    val description: String
)
