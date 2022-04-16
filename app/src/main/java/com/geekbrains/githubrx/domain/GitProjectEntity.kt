package com.geekbrains.githubrx.domain

import com.google.gson.annotations.SerializedName

data class GitProjectEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)

