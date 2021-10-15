package ru.marslab.popularlibraries.view

import ru.marslab.popularlibraries.model.CounterButton

interface MainView {
    fun setButtonText(button: CounterButton?, text: String)
}