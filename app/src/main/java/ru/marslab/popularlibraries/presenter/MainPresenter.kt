package ru.marslab.popularlibraries.presenter

import ru.marslab.popularlibraries.model.CounterButton
import ru.marslab.popularlibraries.model.CountersModel
import ru.marslab.popularlibraries.view.MainView

class MainPresenter {
    private var mainView: MainView? = null

    private val model = CountersModel()

    fun buttonClick(button: CounterButton?) {
        button?.let {
            val nextCount = model.next(button.id)
            mainView?.setButtonText(it, nextCount.toString())
        }
    }

    fun getCounters(): IntArray =
        model.getCounters()


    fun setCounters(it: IntArray) {
        for (i in it.indices) {
            model.set(i, it[i])
            mainView?.setButtonText(
                CounterButton.getButtonById(i),
                it[i].toString()
            )
        }
    }

    fun attach(view: MainView) {
        this.mainView = view
    }

    fun detach() {
        mainView = null
    }
}