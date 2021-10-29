package ru.marslab.popularlibraries.data.repository

import android.util.Log
import com.google.gson.GsonBuilder
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.marslab.popularlibraries.data.retrofit.GithubService
import ru.marslab.popularlibraries.domain.model.GithubUser
import ru.marslab.popularlibraries.domain.repository.GithubRepository

private const val HTTP_LOG_TAG = "HTTP_LOG"

class GithubRepositoryImpl : GithubRepository {

    private val githubOkhttpClient: OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(
                HttpLoggingInterceptor { Log.d(HTTP_LOG_TAG, it) }
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            ).build()

    private val githubRetrofit =
        Retrofit.Builder()
            .baseUrl(GithubService.GITHUB_BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .client(githubOkhttpClient)
            .build()

    private val githubService =
        githubRetrofit.create(GithubService::class.java)

    override fun getUsers(): Single<List<GithubUser>> {
        TODO()
    }

}