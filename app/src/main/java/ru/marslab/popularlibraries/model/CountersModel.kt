package ru.marslab.popularlibraries.model

private const val INIT_COUNTER_VALUE = 0

class CountersModel {

    private val counters = mutableListOf(INIT_COUNTER_VALUE, INIT_COUNTER_VALUE, INIT_COUNTER_VALUE)

    fun getCounters(): IntArray = counters.toIntArray()

    fun next(index: Int): Int =
        ++counters[index]

    fun set(index: Int, value: Int) {
        counters[index] = value
    }
}