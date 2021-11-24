package ru.marslab.popularlibraries.domain.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.marslab.popularlibraries.domain.model.GithubUser
import ru.marslab.popularlibraries.ui.view.UserDetailView
import javax.inject.Inject

class UserDetailPresenter : MvpPresenter<UserDetailView>() {
    var user: GithubUser? = null

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
