package ru.marslab.popularlibraries.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.marslab.popularlibraries.App
import ru.marslab.popularlibraries.R
import ru.marslab.popularlibraries.databinding.FragmentUsersBinding
import ru.marslab.popularlibraries.domain.presenter.UsersPresenter
import ru.marslab.popularlibraries.ui.adapter.UserRVAdapter
import ru.marslab.popularlibraries.ui.util.BackButtonListener
import ru.marslab.popularlibraries.ui.view.UsersView

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance(): UsersFragment = UsersFragment()
    }

    private var _binding: FragmentUsersBinding? = null
    private val binding: FragmentUsersBinding
        get() = checkNotNull(_binding) { getString(R.string.binding_create_error, this::class) }

    private val presenter by moxyPresenter {
        UsersPresenter(
            App.instance.githubRepository,
            App.instance.router
        )
    }

    private val userRVAdapter: UserRVAdapter by lazy {
        UserRVAdapter(presenter.userListPresenter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun init() {
        binding.rvUsers.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = userRVAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        userRVAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onBackPressed(): Boolean =
        presenter.backPressed()
}

