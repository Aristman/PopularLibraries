package ru.marslab.popularlibraries.presenter

import ru.marslab.popularlibraries.model.CounterButton
import ru.marslab.popularlibraries.model.CountersModel
import ru.marslab.popularlibraries.view.MainView

class MainPresenterImpl : MainPresenter {
    private var mainView: MainView? = null

    private val model = CountersModel()

    override fun buttonClick(button: CounterButton?) {
        button?.let {
            val nextCount = model.next(button.id)
            mainView?.setButtonText(it, nextCount.toString())
        }
    }

    override fun getCounters(): IntArray =
        model.getCounters()


    override fun setCounters(it: IntArray) {
        for (i in it.indices) {
            model.set(i, it[i])
            mainView?.setButtonText(
                CounterButton.getButtonById(i),
                it[i].toString()
            )
        }
    }

    override fun attach(view: MainView) {
        this.mainView = view
    }

    override fun detach() {
        mainView = null
    }
}