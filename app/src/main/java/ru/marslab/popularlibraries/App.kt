package ru.marslab.popularlibraries

import android.app.Application
import ru.marslab.popularlibraries.di.AppComponent
import ru.marslab.popularlibraries.di.AppModule
import ru.marslab.popularlibraries.di.DaggerAppComponent

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
