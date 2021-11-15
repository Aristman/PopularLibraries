package ru.marslab.popularlibraries.ui

import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.marslab.popularlibraries.App
import ru.marslab.popularlibraries.R
import ru.marslab.popularlibraries.databinding.ActivityMainBinding
import ru.marslab.popularlibraries.domain.presenter.MainPresenter
import ru.marslab.popularlibraries.ui.util.BackButtonListener
import ru.marslab.popularlibraries.ui.view.MainView
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    private val navigator = AppNavigator(this, R.id.main_container)

    private val presenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        App.instance.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
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
