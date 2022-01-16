package ru.marslab.popularlibraries.domain.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.marslab.popularlibraries.ui.screen.IScreens
import ru.marslab.popularlibraries.ui.view.MainView
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}
