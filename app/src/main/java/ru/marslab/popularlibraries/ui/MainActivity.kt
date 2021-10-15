package ru.marslab.popularlibraries.ui

import android.os.Bundle
import android.view.LayoutInflater
import moxy.MvpAppCompatActivity
import ru.marslab.popularlibraries.databinding.ActivityMainBinding
import ru.marslab.popularlibraries.model.GithubUser
import ru.marslab.popularlibraries.view.MainView

class MainActivity : MvpAppCompatActivity(), MainView {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(LayoutInflater.from(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun displayUser(user: GithubUser) {

    }
}

