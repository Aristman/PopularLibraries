package ru.marslab.popularlibraries.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.marslab.popularlibraries.App
import ru.marslab.popularlibraries.R
import ru.marslab.popularlibraries.databinding.FragmentUsersBinding
import ru.marslab.popularlibraries.domain.presenter.UsersPresenter
import ru.marslab.popularlibraries.ui.adapter.UserRVAdapter
import ru.marslab.popularlibraries.ui.screen.Screens
import ru.marslab.popularlibraries.ui.util.BackButtonListener
import ru.marslab.popularlibraries.ui.util.setToolbarTitle
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
            App.instance.getGithubRepository(),
            App.instance.router,
            Screens()
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
        setToolbarTitle(getString(R.string.app_name))
        initRV()
        initListeners()
    }

    private fun initListeners() {
        binding.btnReload.setOnClickListener {
            presenter.reloadData()
        }
    }

    private fun initRV() {
        binding.rvUsers.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = userRVAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        userRVAdapter.notifyDataSetChanged()
    }

    override fun showErrorToast(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        binding.run {
            loadingIndicator.visibility = View.VISIBLE
            rvUsers.visibility = View.GONE
            btnReload.visibility = View.GONE
        }
    }

    override fun showReload() {
        binding.run {
            loadingIndicator.visibility = View.GONE
            rvUsers.visibility = View.GONE
            btnReload.visibility = View.VISIBLE
        }
    }

    override fun showMainContent() {
        binding.run {
            loadingIndicator.visibility = View.GONE
            rvUsers.visibility = View.VISIBLE
            btnReload.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onBackPressed(): Boolean =
        presenter.backPressed()
}
