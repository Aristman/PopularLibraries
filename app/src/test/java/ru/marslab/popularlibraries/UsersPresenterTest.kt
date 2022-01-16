package ru.marslab.popularlibraries

import com.github.terrakok.cicerone.Router
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import ru.marslab.popularlibraries.domain.presenter.UsersPresenter
import ru.marslab.popularlibraries.domain.repository.GithubRepository
import ru.marslab.popularlibraries.ui.screen.Screens

class UsersPresenterTest {
    private lateinit var usersPresenter: UsersPresenter

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var screens: Screens

    @Mock
    private lateinit var githubRepository: GithubRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        usersPresenter = UsersPresenter()
    }
}
