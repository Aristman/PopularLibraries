package ru.marslab.imageconverter.ui

import android.os.Bundle
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.marslab.imageconverter.App
import ru.marslab.imageconverter.databinding.ActivityMainBinding
import ru.marslab.imageconverter.domain.presenter.MainPresenter
import ru.marslab.imageconverter.ui.view.MainView

class MainActivity : MvpAppCompatActivity(), MainView {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainPresenter by moxyPresenter { MainPresenter(App.instance.mainRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        TODO("Not yet implemented")
    }
}