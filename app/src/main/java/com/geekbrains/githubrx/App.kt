package com.geekbrains.githubrx

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.geekbrains.githubrx.data.retrofit.GitHubAPI
import com.geekbrains.githubrx.data.retrofit.RetrofitRequestImpl
import com.geekbrains.githubrx.domain.RepositoryDetailUser
import com.geekbrains.githubrx.domain.RepositoryList
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class App : Application() {

    /**
     * создаём retrofit за пределами класса RetrofitRequestImpl
     */
    private val apiUrl = "https://api.github.com"
    private val jsonConverterFactory by lazy { GsonConverterFactory.create() }
    private val rxJava3CallAdapterFactory by lazy { RxJava3CallAdapterFactory.create() }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(apiUrl)
            .addCallAdapterFactory(rxJava3CallAdapterFactory)
            .addConverterFactory(jsonConverterFactory)
            .build()
    }

    private val gitHubAPI: GitHubAPI = retrofit.create(GitHubAPI::class.java)

    /**
     * подключаемcя к retrofit как либо запросы
     */
    val getHubListUser: RepositoryList by lazy { RetrofitRequestImpl(gitHubAPI) }

    val getHubDetailUser: RepositoryDetailUser by lazy { RetrofitRequestImpl(gitHubAPI) }
}

val Context.app: App
    get() = applicationContext as App

val Fragment.app: App
    get() = requireActivity().app
