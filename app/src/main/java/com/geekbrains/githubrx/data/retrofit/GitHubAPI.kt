package com.geekbrains.githubrx.data.retrofit


import com.geekbrains.githubrx.domain.GitProjectEntity
import com.geekbrains.githubrx.domain.GitProjectUserDetail
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubAPI {

    @GET("/users/{user}/repos")
    fun listRepos(@Path("user") user: String): Single<List<GitProjectEntity>>

    @GET("/users?since=68413502")
    fun listUsers(): Single<List<GitProjectEntity>>

    @GET("/users/{user}")
    suspend fun userDetail(@Path("user") user: String): GitProjectUserDetail

}