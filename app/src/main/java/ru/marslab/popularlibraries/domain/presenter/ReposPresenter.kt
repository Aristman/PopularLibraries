package ru.marslab.popularlibraries.domain.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.marslab.popularlibraries.domain.model.GithubUser
import ru.marslab.popularlibraries.domain.repository.GithubRepository
import ru.marslab.popularlibraries.ui.screen.IScreens
import ru.marslab.popularlibraries.ui.view.UserReposView

class ReposPresenter(
    private val userRepository: GithubRepository,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<UserReposView>() {
    var user: GithubUser? = null


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}