package ru.marslab.popularlibraries.domain.presenter

import ru.marslab.popularlibraries.domain.adapter.RepoItemView
import ru.marslab.popularlibraries.domain.model.GithubRepo

class RepoListPresenter : IRepoListPresenter {
    val repos = mutableListOf<GithubRepo>()
    override var itemClickListener: ((RepoItemView) -> Unit)? = null


    override fun bindView(view: RepoItemView) {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }


}
