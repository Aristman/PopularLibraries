package ru.marslab.popularlibraries

import android.app.Application
import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import ru.marslab.popularlibraries.data.repository.CacheRepositoryImpl
import ru.marslab.popularlibraries.data.repository.GithubRepositoryImpl
import ru.marslab.popularlibraries.data.room.GithubDatabase
import ru.marslab.popularlibraries.domain.repository.CacheRepository
import ru.marslab.popularlibraries.domain.repository.GithubRepository

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    private val githubDb = Room
        .databaseBuilder(this, GithubDatabase::class.java, "github_database.db")
        .build()

    val navigatorHolder = cicerone.getNavigatorHolder()
    val router = cicerone.router
    val githubRepository: GithubRepository = GithubRepositoryImpl()
    val cacheRepository: CacheRepository = CacheRepositoryImpl(githubDb)

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}