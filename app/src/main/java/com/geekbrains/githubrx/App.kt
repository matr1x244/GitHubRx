package com.geekbrains.githubrx

import android.app.Application
import android.content.Context
import com.geekbrains.githubrx.data.retrofit.RetrofitRequestImpl
import com.geekbrains.githubrx.domain.Repository


class App : Application() {

    val getRepository: Repository by lazy { RetrofitRequestImpl() }

}

val Context.app: App
    get() = applicationContext as App
