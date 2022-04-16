package com.geekbrains.githubrx.data.retrofit

import com.geekbrains.githubrx.domain.GitProjectEntity
import com.geekbrains.githubrx.domain.Repository
import io.reactivex.rxjava3.core.Single


class LocalRequestImpl : Repository {

    override fun observeReposForUser(username: String): Single<List<GitProjectEntity>> {
        val localList = listOf(
            GitProjectEntity(0, "!!!", "", "")
        )
        return Single.just(localList)
    }

    override fun observerReposListUser(): Single<List<GitProjectEntity>> {
        val localList = listOf(
            GitProjectEntity(1, "???", "", ""),
            GitProjectEntity(2, "&&&", "", "")
        )
        return Single.just(localList)
    }
}