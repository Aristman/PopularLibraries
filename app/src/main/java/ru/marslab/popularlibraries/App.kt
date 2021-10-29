package ru.marslab.popularlibraries

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import ru.marslab.popularlibraries.data.repository.GithubRepositoryImpl
import ru.marslab.popularlibraries.domain.repository.GithubRepository

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    val navigatorHolder = cicerone.getNavigatorHolder()
    val router = cicerone.router
    val githubRepository: GithubRepository = GithubRepositoryImpl()

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}