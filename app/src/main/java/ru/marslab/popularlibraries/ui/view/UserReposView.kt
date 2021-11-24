package ru.marslab.popularlibraries.ui.view

import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserReposView : MvpView {
    fun init()
    fun updateList()

    @StateStrategyType(SkipStrategy::class)
    fun showErrorToast(message: String?)
    fun showLoading()
    fun showReload()
    fun showMainContent()
}
