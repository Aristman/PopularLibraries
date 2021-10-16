package ru.marslab.popularlibraries.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.marslab.popularlibraries.data.repository.GithubRepositoryMocImpl
import ru.marslab.popularlibraries.databinding.ActivityMainBinding
import ru.marslab.popularlibraries.domain.presenter.MainPresenter
import ru.marslab.popularlibraries.ui.adapter.UserRVAdapter
import ru.marslab.popularlibraries.ui.view.MainView

class MainActivity : MvpAppCompatActivity(), MainView {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(LayoutInflater.from(this))
    }

    private val presenter by moxyPresenter { MainPresenter(GithubRepositoryMocImpl()) }

    private val userRVAdapter: UserRVAdapter by lazy {
        UserRVAdapter(presenter.userListPresenter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun init() {
        binding.rvUsers.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userRVAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        userRVAdapter.notifyDataSetChanged()
    }
}

