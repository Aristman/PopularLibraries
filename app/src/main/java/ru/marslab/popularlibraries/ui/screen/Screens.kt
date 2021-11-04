package ru.marslab.popularlibraries.ui.screen

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.marslab.popularlibraries.domain.model.GithubUser
import ru.marslab.popularlibraries.ui.fragment.UserDetailFragment
import ru.marslab.popularlibraries.ui.fragment.UsersFragment

class Screens : IScreens {
    override fun users(): Screen =
        FragmentScreen { UsersFragment.newInstance() }

    override fun userDetail(user: GithubUser): Screen =
        FragmentScreen { UserDetailFragment.newInstance(user) }

    override fun userRepos(user: GithubUser): Screen {
        TODO("Not yet implemented")
    }
}