package ru.marslab.popularlibraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.marslab.popularlibraries.R
import ru.marslab.popularlibraries.databinding.FragmentUserDetailBinding
import ru.marslab.popularlibraries.domain.model.GithubUser
import ru.marslab.popularlibraries.domain.presenter.UserDetailPresenter
import ru.marslab.popularlibraries.ui.util.BackButtonListener
import ru.marslab.popularlibraries.ui.view.UserDetailView

class UserDetailFragment : MvpAppCompatFragment(), UserDetailView, BackButtonListener {
    companion object {
        private const val USER_TAG = "user_tag"
        fun newInstance(user: GithubUser): UserDetailFragment {
            val args = Bundle().apply {
                putParcelable(USER_TAG, user)
            }
            val fragment = UserDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentUserDetailBinding? = null
    private val binding: FragmentUserDetailBinding
        get() = checkNotNull(_binding) { getString(R.string.binding_create_error, this::class) }

    private val presenter by moxyPresenter { UserDetailPresenter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun init() {
        presenter.user = arguments?.getParcelable(USER_TAG)
        presenter.user?.login?.let { showUserLogin(it) }
    }

    override fun showUserLogin(login: String) {
        binding.userLogin.text = login
    }

    override fun onBackPressed(): Boolean =
        presenter.backPressed()

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
