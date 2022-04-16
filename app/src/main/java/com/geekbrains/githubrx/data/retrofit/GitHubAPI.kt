package com.geekbrains.githubrx.data.retrofit

import com.geekbrains.githubrx.domain.GitProjectEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubAPI {

    @GET("/users/{user}/repos")
    fun listRepos(@Path("user") user: String): Single<List<GitProjectEntity>>

    @GET("/users")
    fun listUsers(): Single<List<GitProjectEntity>>

}