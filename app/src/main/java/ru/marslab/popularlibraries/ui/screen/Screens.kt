package ru.marslab.popularlibraries.ui.screen

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.marslab.popularlibraries.ui.UsersFragment

class Screens : IScreens {
    override fun users(): Screen =
        FragmentScreen { UsersFragment.newInstance() }
}