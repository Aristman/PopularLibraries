package ru.marslab.popularlibraries.ui.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserReposView : MvpView {
    fun init()

}
