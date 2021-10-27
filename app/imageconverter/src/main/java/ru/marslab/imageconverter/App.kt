package ru.marslab.imageconverter

import android.app.Application
import io.reactivex.plugins.RxJavaPlugins
import ru.marslab.imageconverter.data.repository.MainRepositoryImpl
import ru.marslab.imageconverter.domain.repository.MainRepository

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    val mainRepository: MainRepository = MainRepositoryImpl()

    override fun onCreate() {
        super.onCreate()
        instance = this
        RxJavaPlugins.setErrorHandler { }
    }
}