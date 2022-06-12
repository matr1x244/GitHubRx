package com.geekbrains.githubrx.di.koin

import com.geekbrains.githubrx.data.retrofit.GitHubAPI
import com.geekbrains.githubrx.data.retrofit.RetrofitRequestImpl
import com.geekbrains.githubrx.domain.RepositoryDetailUser
import com.geekbrains.githubrx.domain.RepositoryList
import com.geekbrains.githubrx.ui.main.detail.DetailViewModel
import com.geekbrains.githubrx.ui.main.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModuleKoin = module {

    single<RepositoryList> { RetrofitRequestImpl(get()) }
    single<RepositoryDetailUser> { RetrofitRequestImpl(get()) }

    single<GitHubAPI> { get<Retrofit>().create(GitHubAPI::class.java) }

    single<String>(named("api_url")) { "https://api.github.com" }
    single<String>(named("api_url_version")) { "https://api.github.com/v2" }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named("api_url")))
            .addCallAdapterFactory(get())
            .addConverterFactory(get())
            .build()
    }

    factory<Converter.Factory> { GsonConverterFactory.create() }
    factory<CallAdapter.Factory> { RxJava3CallAdapterFactory.create() }


    /**
     * import org.koin.androidx.viewmodel.ext.android.viewModel
     */
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}