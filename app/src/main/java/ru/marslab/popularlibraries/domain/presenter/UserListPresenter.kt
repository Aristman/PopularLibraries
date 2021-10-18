package ru.marslab.popularlibraries.domain.presenter

import ru.marslab.popularlibraries.domain.adapter.UserItemView
import ru.marslab.popularlibraries.domain.model.GithubUser

class UserListPresenter : IUserListPresenter {
    val users = mutableListOf<GithubUser>()

    override var itemClickListener: ((UserItemView) -> Unit)? = null

    override fun bindView(view: UserItemView) =
        view.setLogin(users[view.pos].login)

    override fun getCount(): Int =
        users.size
}