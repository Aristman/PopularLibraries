package ru.marslab.popularlibraries.data.repository

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import ru.marslab.popularlibraries.domain.model.GithubUser
import ru.marslab.popularlibraries.domain.repository.Constant
import ru.marslab.popularlibraries.domain.repository.GithubRepository
import kotlin.random.Random

private const val PERCENT_SUCCESSFUL_REQUEST = 50
private const val MAX_PERCENT = 100
private const val LOAD_DELAY = 2000L
private const val COUNT_USERS = 55

class GithubRepositoryMocImpl : GithubRepository {
    private val users: List<GithubUser> =
        (0..COUNT_USERS).map { GithubUser("User $it") }

    override fun getUsers(): Observable<List<GithubUser>> {
        val variantResponse = Random.nextInt(MAX_PERCENT)
        Thread.sleep(LOAD_DELAY)
        return Observable.fromCallable {
            if (variantResponse > PERCENT_SUCCESSFUL_REQUEST) {
                users
            } else {
                error(Constant.DATA_LOAD_ERROR)
            }
        }.subscribeOn(Schedulers.io())
    }
}