package ru.marslab.popularlibraries.ui.screen

import com.github.terrakok.cicerone.Screen
import ru.marslab.popularlibraries.domain.model.GithubUser

interface IScreens {
    fun users(): Screen
    fun userDetail(user: GithubUser): Screen
}