package ru.marslab.popularlibraries.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    val name: String,
    val password: String
) : Parcelable