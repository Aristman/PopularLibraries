package ru.marslab.popularlibraries

import android.app.Application
import android.util.Log
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.marslab.popularlibraries.data.repository.GithubRepositoryWithCacheImpl
import ru.marslab.popularlibraries.data.repository.RoomGithubCacheImpl
import ru.marslab.popularlibraries.data.retrofit.GithubService
import ru.marslab.popularlibraries.data.room.GithubDatabase
import ru.marslab.popularlibraries.domain.repository.GithubRepository
import ru.marslab.popularlibraries.domain.util.IRoomGithubCache
import ru.marslab.popularlibraries.ui.util.NetworkStatus

class App : Application() {
    companion object {
        private const val HTTP_LOG_TAG = "HTTP_LOG"
        lateinit var instance: App
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    private val githubOkhttpClient: OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(
                HttpLoggingInterceptor { Log.d(HTTP_LOG_TAG, it) }
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            ).build()

    private val githubRetrofit =
        Retrofit.Builder()
            .baseUrl(GithubService.GITHUB_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .client(githubOkhttpClient)
            .build()

    private val githubService =
        githubRetrofit.create(GithubService::class.java)

    private val networkStatus: NetworkStatus by lazy {
        NetworkStatus(baseContext)
    }

    fun roomCacheRepository(): IRoomGithubCache =
        RoomGithubCacheImpl(GithubDatabase.getInstance())

    fun getGithubRepository(): GithubRepository =
        GithubRepositoryWithCacheImpl(githubService, networkStatus, roomCacheRepository())

    val navigatorHolder = cicerone.getNavigatorHolder()
    val router = cicerone.router

    override fun onCreate() {
        super.onCreate()
        GithubDatabase.create(this)
        instance = this
    }
}
