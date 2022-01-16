package ru.marslab.popularlibraries.di

import dagger.Component
import ru.marslab.popularlibraries.domain.presenter.MainPresenter
import ru.marslab.popularlibraries.domain.presenter.UserDetailPresenter
import ru.marslab.popularlibraries.domain.presenter.UserReposPresenter
import ru.marslab.popularlibraries.domain.presenter.UsersPresenter
import ru.marslab.popularlibraries.ui.MainActivity
import ru.marslab.popularlibraries.ui.fragment.UserDetailFragment
import ru.marslab.popularlibraries.ui.fragment.UserReposFragment
import ru.marslab.popularlibraries.ui.fragment.UsersFragment
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ResourceModule::class
    ]
)

interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersFragment: UsersFragment)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userDetailPresenter: UserDetailPresenter)
    fun inject(userDetailFragment: UserDetailFragment)
    fun inject(userReposPresenter: UserReposPresenter)
    fun inject(userReposFragment: UserReposFragment)
}