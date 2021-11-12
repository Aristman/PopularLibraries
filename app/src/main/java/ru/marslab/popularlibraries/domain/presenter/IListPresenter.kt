package ru.marslab.popularlibraries.domain.presenter

import ru.marslab.popularlibraries.domain.adapter.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}
