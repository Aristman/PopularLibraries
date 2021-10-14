package ru.marslab.popularlibraries.presenter

import ru.marslab.popularlibraries.model.CounterButton
import ru.marslab.popularlibraries.view.MainView

interface MainPresenter {
    fun buttonClick(button: CounterButton?)

    fun getCounters(): IntArray

    fun setCounters(it: IntArray)

    fun attach(view: MainView)

    fun detach()
}