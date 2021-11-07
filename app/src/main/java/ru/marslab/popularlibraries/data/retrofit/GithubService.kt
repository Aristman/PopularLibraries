package ru.marslab.popularlibraries.data.retrofit

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url
import ru.marslab.popularlibraries.data.model.GithubRepoNW
import ru.marslab.popularlibraries.data.model.GithubUserNW

interface GithubService {
    @GET("/users")
    fun getUsers(): Single<List<GithubUserNW>>

    @GET
    fun getUserRepos(
        @Url url: String
    ): Single<List<GithubRepoNW>>
}
