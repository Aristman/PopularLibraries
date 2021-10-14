package ru.marslab.popularlibraries.model

sealed class CounterButton {
    abstract val id: Int

    object ButtonOne : CounterButton() {
        override val id: Int
            get() = 0
    }

    object ButtonTwo : CounterButton() {
        override val id: Int
            get() = 1
    }

    object ButtonThree : CounterButton() {
        override val id: Int
            get() = 2
    }

    companion object {
        fun getButtonById(id: Int): CounterButton? =
            when (id) {
                ButtonOne.id -> ButtonOne
                ButtonTwo.id -> ButtonTwo
                ButtonThree.id -> ButtonThree
                else -> null
            }
    }
}