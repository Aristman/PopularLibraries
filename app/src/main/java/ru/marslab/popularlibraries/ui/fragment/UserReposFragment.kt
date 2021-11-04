package ru.marslab.popularlibraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.marslab.popularlibraries.App
import ru.marslab.popularlibraries.R
import ru.marslab.popularlibraries.databinding.FragmentUserReposBinding
import ru.marslab.popularlibraries.domain.model.GithubUser
import ru.marslab.popularlibraries.domain.presenter.ReposPresenter
import ru.marslab.popularlibraries.ui.screen.Screens
import ru.marslab.popularlibraries.ui.util.BackButtonListener
import ru.marslab.popularlibraries.ui.view.UserReposView

class UserReposFragment : MvpAppCompatFragment(), UserReposView, BackButtonListener {
    companion object {
        private const val USER_TAG = "user_tag"
        fun newInstance(user: GithubUser): UserReposFragment {
            val args = Bundle().apply {
                putParcelable(USER_TAG, user)
            }
            val fragment = UserReposFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentUserReposBinding? = null
    private val binding: FragmentUserReposBinding
        get() = checkNotNull(_binding) { getString(R.string.binding_create_error, this::class) }

    private val presenter by moxyPresenter {
        ReposPresenter(
            App.instance.githubRepository,
            App.instance.router,
            Screens()
        )
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
        (requireActivity() as AppCompatActivity).supportActionBar?.title = presenter.user?.login
    }

    override fun updateList() {
        TODO("Not yet implemented")
    }

    override fun showErrorToast(message: String?) {
        TODO("Not yet implemented")
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun showReload() {
        TODO("Not yet implemented")
    }

    override fun showMainContent() {
        TODO("Not yet implemented")
    }

    override fun onBackPressed(): Boolean =
        presenter.backPressed()

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}