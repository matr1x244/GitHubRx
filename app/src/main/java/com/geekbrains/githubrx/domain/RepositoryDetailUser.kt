package com.geekbrains.githubrx.domain


interface RepositoryDetailUser {
    suspend fun observerUserDetail(username: String): GitProjectUserDetail
}