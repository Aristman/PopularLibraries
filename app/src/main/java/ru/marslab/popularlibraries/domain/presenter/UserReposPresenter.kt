package ru.marslab.popularlibraries.domain.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import ru.marslab.popularlibraries.domain.model.GithubUser
import ru.marslab.popularlibraries.domain.repository.GithubRepository
import ru.marslab.popularlibraries.ui.screen.IScreens
import ru.marslab.popularlibraries.ui.view.UserReposView
import javax.inject.Inject

class UserReposPresenter : MvpPresenter<UserReposView>() {
    var user: GithubUser? = null
    val repoListPresenter = RepoListPresenter()

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var githubRepository: GithubRepository

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
        user?.let {
            githubRepository.getUserRepos(it)
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
