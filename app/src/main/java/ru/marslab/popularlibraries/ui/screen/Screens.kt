package ru.marslab.popularlibraries.ui.screen

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.marslab.popularlibraries.domain.model.GithubRepo
import ru.marslab.popularlibraries.domain.model.GithubUser
import ru.marslab.popularlibraries.ui.fragment.RepoDetailFragment
import ru.marslab.popularlibraries.ui.fragment.UserDetailFragment
import ru.marslab.popularlibraries.ui.fragment.UserReposFragment
import ru.marslab.popularlibraries.ui.fragment.UsersFragment

class Screens : IScreens {
    override fun users(): Screen =
        FragmentScreen { UsersFragment.newInstance() }

    override fun userDetail(user: GithubUser): Screen =
        FragmentScreen { UserDetailFragment.newInstance(user) }

    override fun userRepos(user: GithubUser): Screen =
        FragmentScreen { UserReposFragment.newInstance(user) }

    override fun repoDetails(repo: GithubRepo): Screen =
        FragmentScreen { RepoDetailFragment.newInstance(repo) }
}
