package ru.marslab.popularlibraries.domain.presenter

import moxy.MvpPresenter
import ru.marslab.popularlibraries.domain.repository.GithubRepository
import ru.marslab.popularlibraries.ui.view.MainView

class MainPresenter(private val userRepository: GithubRepository) : MvpPresenter<MainView>() {
    val userListPresenter = UserListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        userListPresenter.itemClickListener = { userItemView ->
            // TODO("Переход на экран детализации юзера")
        }
    }

    private fun loadData() {
        val users = userRepository.getUsers()
        userListPresenter.users.addAll(users)
        viewState.updateList()
    }
}

