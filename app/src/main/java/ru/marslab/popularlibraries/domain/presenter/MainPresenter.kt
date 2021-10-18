package ru.marslab.popularlibraries.domain.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.marslab.popularlibraries.ui.screen.IScreens
import ru.marslab.popularlibraries.ui.view.MainView

class MainPresenter(private val router: Router, private val screens: IScreens) :
    MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}

