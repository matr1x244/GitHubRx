package com.geekbrains.githubrx.data.retrofit

import com.geekbrains.githubrx.domain.GitProjectEntity
import com.geekbrains.githubrx.domain.Repository
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitRequestImpl : Repository {

    private val baseUrl = ("https://api.github.com")

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api: GitHubAPI = retrofit.create(GitHubAPI::class.java)

    override fun observeReposForUser(username: String): Single<List<GitProjectEntity>> {
        return api.listRepos(username)
    }

    override fun observerReposListUser(): Single<List<GitProjectEntity>> {
        return api.listUsers()
    }

    override fun observerLogin(user: String): Single<List<GitProjectEntity>> {
        return api.login(user)
    }
}