package com.geekbrains.githubrx.data.retrofit

import com.geekbrains.githubrx.domain.GitProjectEntity
import com.geekbrains.githubrx.domain.GitProjectUserDetail
import com.geekbrains.githubrx.domain.RepositoryDetailUser
import com.geekbrains.githubrx.domain.RepositoryList
import io.reactivex.rxjava3.core.Single


class RetrofitRequestImpl(private val api: GitHubAPI) : RepositoryList, RepositoryDetailUser {

    override fun observeReposForUser(username: String): Single<List<GitProjectEntity>> {
        return api.listRepos(username)
    }

    override fun observerReposListUser(): Single<List<GitProjectEntity>> {
        return api.listUsers()
    }

    override suspend fun observerUserDetail(username: String): GitProjectUserDetail {
        return api.userDetail(username)
    }
}