package com.geekbrains.githubrx.data.local

import com.geekbrains.githubrx.domain.GitProjectEntity
import com.geekbrains.githubrx.domain.GitProjectUserDetail
import com.geekbrains.githubrx.domain.RepositoryDetailUser
import com.geekbrains.githubrx.domain.RepositoryList
import io.reactivex.rxjava3.core.Single


class LocalRequestImpl : RepositoryList, RepositoryDetailUser {

    override fun observeReposForUser(username: String): Single<List<GitProjectEntity>> {
        val localList = listOf(
            GitProjectEntity(0, "!!!", "", "", "location1")
        )
        return Single.just(localList)
    }


    override fun observerReposListUser(): Single<List<GitProjectEntity>> {
        val localList = listOf(
            GitProjectEntity(1, "???", "", "", "location1"),
            GitProjectEntity(2, "&&&", "", "", "location2")
        )
        return Single.just(localList)
    }

    override suspend fun observerUserDetail(username: String): GitProjectUserDetail {
        val localList = GitProjectUserDetail(55, "&&&111", "", "location3", "sad")
        return localList
    }
}