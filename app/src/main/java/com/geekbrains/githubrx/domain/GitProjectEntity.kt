package com.geekbrains.githubrx.domain

data class GitProjectEntity(
    val id: Int,
    val login: String,
    val name: String,
    val avatarUrl: String
)