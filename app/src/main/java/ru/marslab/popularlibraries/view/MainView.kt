package ru.marslab.popularlibraries.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.marslab.popularlibraries.model.GithubUser

interface MainView : MvpView {

    @AddToEndSingle
    fun displayUser(user: GithubUser)
}