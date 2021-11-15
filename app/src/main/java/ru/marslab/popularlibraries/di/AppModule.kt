package ru.marslab.popularlibraries.di

import dagger.Module
import dagger.Provides
import ru.marslab.popularlibraries.App

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

}