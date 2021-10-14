package ru.marslab.popularlibraries

import android.app.Application
import ru.marslab.popularlibraries.presenter.MainPresenter
import ru.marslab.popularlibraries.presenter.MainPresenterImpl

class App : Application() {

    private val presenter: MainPresenter = MainPresenterImpl()

    fun getPresenter(): MainPresenter =
        presenter
}