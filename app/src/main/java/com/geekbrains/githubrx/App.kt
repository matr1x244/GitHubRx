package com.geekbrains.githubrx

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.geekbrains.githubrx.data.local.LocalRequestImpl
import com.geekbrains.githubrx.data.retrofit.RetrofitRequestImpl
import com.geekbrains.githubrx.domain.RepositoryList
import com.geekbrains.githubrx.domain.RepositoryDetailUser


class App : Application() {

    val getHubListUser: RepositoryList by lazy { RetrofitRequestImpl() }

    val getHubDetailUser: RepositoryDetailUser by lazy { RetrofitRequestImpl() }
}

val Context.app: App
    get() = applicationContext as App

val Fragment.app: App
    get() = requireActivity().app
