package com.geekbrains.githubrx.data.retrofit

import com.geekbrains.githubrx.domain.GitProjectEntity
import com.geekbrains.githubrx.domain.GitProjectUserDetail
import com.geekbrains.githubrx.domain.RepositoryDetailUser
import com.geekbrains.githubrx.domain.RepositoryList
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit


class RetrofitRequestImpl(private val retrofit: Retrofit) : RepositoryList, RepositoryDetailUser {

    private val api: GitHubAPI = retrofit.create(GitHubAPI::class.java)

    override fun observeReposForUser(username: String): Single<List<GitProjectEntity>> {
        return api.listRepos(username)
    }

    override fun observerReposListUser(): Single<List<GitProjectEntity>> {
        return api.listUsers()
    }

    override fun observerUserDetail(username: String): Single<List<GitProjectUserDetail>> {
        return api.userDetail(username)
    }
}