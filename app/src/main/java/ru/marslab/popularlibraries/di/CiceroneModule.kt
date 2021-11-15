package ru.marslab.popularlibraries.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import ru.marslab.popularlibraries.ui.screen.IScreens
import ru.marslab.popularlibraries.ui.screen.Screens
import javax.inject.Singleton

@Module
class CiceroneModule {

    private val cicerone = Cicerone.create()

    @Provides
    fun provideCicerone(): Cicerone<Router> =
        cicerone

    @Singleton
    @Provides
    fun provideNavigatorHolder(): NavigatorHolder =
        cicerone.getNavigatorHolder()

    @Singleton
    @Provides
    fun provideRouter(): Router =
        cicerone.router

    @Singleton
    @Provides
    fun provideScreens(): IScreens =
        Screens()
}