package ru.marslab.popularlibraries.data.retrofit

import io.reactivex.Single
import retrofit2.http.GET
import ru.marslab.popularlibraries.data.model.GithubUserNW

interface GithubService {
    companion object {
        const val GITHUB_BASE_URL = "https://api.github.com/"
    }

    @GET("users")
    fun getUsers(): Single<List<GithubUserNW>>
}