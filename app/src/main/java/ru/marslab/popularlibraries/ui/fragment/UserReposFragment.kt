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
import ru.marslab.popularlibraries.databinding.FragmentUserReposBinding
import ru.marslab.popularlibraries.domain.model.GithubUser
import ru.marslab.popularlibraries.domain.presenter.UserReposPresenter
import ru.marslab.popularlibraries.ui.adapter.ReposRVAdapter
import ru.marslab.popularlibraries.ui.util.BackButtonListener
import ru.marslab.popularlibraries.ui.util.setToolbarTitle
import ru.marslab.popularlibraries.ui.view.UserReposView

class UserReposFragment : MvpAppCompatFragment(), UserReposView, BackButtonListener {
    companion object {
        private const val USER_TAG = "user_tag"
        fun newInstance(user: GithubUser): UserReposFragment {
            val args = Bundle().apply {
                putParcelable(USER_TAG, user)
            }
            val fragment = UserReposFragment().apply {
                App.instance.appComponent.inject(this)
            }
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentUserReposBinding? = null
    private val binding: FragmentUserReposBinding
        get() = checkNotNull(_binding) { getString(R.string.binding_create_error, this::class) }

    private val presenter by moxyPresenter {
        UserReposPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    private fun initListeners() {
        binding.btnReload.setOnClickListener {
            presenter.reloadData()
        }
    }

    private val reposRVAdapter: ReposRVAdapter by lazy {
        ReposRVAdapter(presenter.repoListPresenter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserReposBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun init() {
        presenter.user = arguments?.getParcelable(USER_TAG)
        presenter.user?.login?.let { setToolbarTitle(it) }
        initRv()
        initListeners()
    }

    private fun initRv() {
        binding.userRepos.run {
            adapter = reposRVAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        reposRVAdapter.notifyDataSetChanged()
    }

    override fun showErrorToast(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        binding.run {
            loadingIndicator.visibility = View.VISIBLE
            userRepos.visibility = View.GONE
            btnReload.visibility = View.GONE
        }
    }

    override fun showReload() {
        binding.run {
            loadingIndicator.visibility = View.GONE
            userRepos.visibility = View.GONE
            btnReload.visibility = View.VISIBLE
        }
    }

    override fun showMainContent() {
        binding.run {
            loadingIndicator.visibility = View.GONE
            userRepos.visibility = View.VISIBLE
            btnReload.visibility = View.GONE
        }
    }

    override fun onBackPressed(): Boolean =
        presenter.backPressed()

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
