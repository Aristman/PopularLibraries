package ru.marslab.popularlibraries.domain.presenter

import moxy.MvpPresenter
import ru.marslab.popularlibraries.domain.model.GithubRepo
import ru.marslab.popularlibraries.ui.view.RepoDetailView

class RepoDetailPresenter : MvpPresenter<RepoDetailView>() {
    var repo: GithubRepo? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun updateRepoInfo() {
        repo?.let {
            viewState.setRepoName(it.name)
            viewState.setRepoForks(it.forks)
            it.description?.let { it1 -> viewState.setRepoDescription(it1) }
        }
    }

    fun backPressed(): Boolean {
        return true
    }
}
