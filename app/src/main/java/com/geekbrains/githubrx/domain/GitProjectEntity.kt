package com.geekbrains.githubrx.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitProjectEntity(
    val id: Int = 0,
    val login: String = "",
    val name: String = "",
    val avatarUrl: String = ""
): Parcelable