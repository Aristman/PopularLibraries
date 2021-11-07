package ru.marslab.popularlibraries.domain.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import ru.marslab.popularlibraries.domain.model.GithubUser
import ru.marslab.popularlibraries.domain.repository.GithubRepository
import ru.marslab.popularlibraries.ui.screen.IScreens
import ru.marslab.popularlibraries.ui.view.UserReposView

class UserReposPresenter(
    private val userRepository: GithubRepository,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<UserReposView>() {
    var user: GithubUser? = null
    val repoListPresenter = RepoListPresenter()


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadRepos()
        repoListPresenter.itemClickListener = { repoItemView ->
            val githubRepo = repoListPresenter.repos[repoItemView.pos]
            router.navigateTo(screens.repoDetails(githubRepo))
        }
    }

    private fun loadRepos() {
        viewState.showLoading()
        user?.reposUrl?.let {
            userRepository.getUserRepos(it)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        repoListPresenter.repos.addAll(result)
                        viewState.updateList()
                        viewState.showMainContent()
                    },
                    { error ->
                        viewState.showErrorToast(error.message)
                        viewState.showReload()
                    }
                )
        }
    }

    fun backPressed(): Boolean {
        return true
    }

    fun reloadData() {
        loadRepos()
    }
}