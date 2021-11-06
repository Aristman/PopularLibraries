package ru.marslab.popularlibraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.marslab.popularlibraries.R
import ru.marslab.popularlibraries.databinding.FragmentRepoDetailBinding
import ru.marslab.popularlibraries.domain.model.GithubRepo
import ru.marslab.popularlibraries.domain.presenter.RepoDetailPresenter
import ru.marslab.popularlibraries.ui.util.BackButtonListener
import ru.marslab.popularlibraries.ui.util.setToolbarTitle
import ru.marslab.popularlibraries.ui.view.RepoDetailView

class RepoDetailFragment : MvpAppCompatFragment(), RepoDetailView, BackButtonListener {
    companion object {
        private const val REPO_TAG = "user_tag"
        fun newInstance(repo: GithubRepo): RepoDetailFragment {
            val args = Bundle().apply {
                putParcelable(REPO_TAG, repo)
            }
            val fragment = RepoDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentRepoDetailBinding? = null
    private val binding: FragmentRepoDetailBinding
        get() = checkNotNull(_binding) { getString(R.string.binding_create_error, this::class) }

    private val presenter by moxyPresenter { RepoDetailPresenter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepoDetailBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun init() {
        presenter.repo = arguments?.getParcelable(REPO_TAG)
        presenter.repo?.name?.let { setToolbarTitle(it) }
        presenter.updateRepoInfo()
    }

    override fun setRepoName(name: String) {
        binding.repoName.text = name
    }

    override fun setRepoForks(forks: Int) {
        binding.repoForks.text = forks.toString()
    }

    override fun setRepoDescription(text: String) {
        binding.repoDescription.text = text
    }

    override fun onBackPressed(): Boolean =
        presenter.backPressed()

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
