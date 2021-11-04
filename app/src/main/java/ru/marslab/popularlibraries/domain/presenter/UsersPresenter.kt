package ru.marslab.popularlibraries.domain.presenter

import android.annotation.SuppressLint
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import ru.marslab.popularlibraries.domain.repository.GithubRepository
import ru.marslab.popularlibraries.ui.screen.IScreens
import ru.marslab.popularlibraries.ui.view.UsersView

class UsersPresenter(
    private val userRepository: GithubRepository,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<UsersView>() {
    val userListPresenter = UserListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        userListPresenter.itemClickListener = { userItemView ->
            val githubUser = userListPresenter.users[userItemView.pos]
            router.navigateTo(screens.userRepos(githubUser))
        }
    }

    @SuppressLint("CheckResult")
    private fun loadData() {
        viewState.showLoading()
        userRepository.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    userListPresenter.users.addAll(it)
                    viewState.updateList()
                    viewState.showMainContent()

                },
                {
                    viewState.showErrorToast(it.message)
                    viewState.showReload()
                }
            )
    }

    fun reloadData() {
        loadData()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}