package com.geekbrains.githubrx.domain

import io.reactivex.rxjava3.core.Single


interface RepositoryDetailUser {
    fun observerUserDetail(username: String): Single<List<GitProjectUserDetail>>
}