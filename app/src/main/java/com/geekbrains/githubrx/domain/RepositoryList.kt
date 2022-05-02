package com.geekbrains.githubrx.domain

import io.reactivex.rxjava3.core.Single


interface RepositoryList {
    fun observeReposForUser(username: String): Single<List<GitProjectEntity>>
    fun observerReposListUser(): Single<List<GitProjectEntity>>
}