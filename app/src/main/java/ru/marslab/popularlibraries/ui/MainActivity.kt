package ru.marslab.popularlibraries.ui

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.marslab.popularlibraries.App
import ru.marslab.popularlibraries.R
import ru.marslab.popularlibraries.databinding.ActivityMainBinding
import ru.marslab.popularlibraries.domain.presenter.MainPresenter
import ru.marslab.popularlibraries.ui.screen.Screens
import ru.marslab.popularlibraries.ui.util.BackButtonListener
import ru.marslab.popularlibraries.ui.view.MainView

class MainActivity : MvpAppCompatActivity(), MainView {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val navigator = AppNavigator(this, R.id.main_container)
    private val presenter by moxyPresenter { MainPresenter(App.instance.router, Screens()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.onBackPressed()) {
                return
            }
            presenter.backClicked()
        }
    }
}

