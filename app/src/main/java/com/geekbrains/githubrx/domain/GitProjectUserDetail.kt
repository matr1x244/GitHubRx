package com.geekbrains.githubrx.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitProjectUserDetail(
    val id: Int,
    val login: String,
    val name: String?,
    val avatarUrl: String,
    val location: String?
): Parcelable
