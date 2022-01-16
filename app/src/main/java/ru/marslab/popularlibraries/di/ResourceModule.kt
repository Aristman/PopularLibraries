package ru.marslab.popularlibraries.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.marslab.popularlibraries.App

@Module
class ResourceModule {

    @Provides
    fun provideContext(app: App): Context =
        app.applicationContext
}