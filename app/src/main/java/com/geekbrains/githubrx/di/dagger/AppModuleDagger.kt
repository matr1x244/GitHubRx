package com.geekbrains.githubrx.di.dagger

import android.content.Context
import com.geekbrains.githubrx.data.retrofit.GitHubAPI
import com.geekbrains.githubrx.data.retrofit.RetrofitRequestImpl
import com.geekbrains.githubrx.domain.RepositoryDetailUser
import com.geekbrains.githubrx.domain.RepositoryList
import dagger.Module
import dagger.Provides
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModuleDagger(private val context: Context) {

    @Singleton
    @Provides
    fun provideGitHubApi(retrofit: Retrofit): GitHubAPI {
        return retrofit.create(GitHubAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideRepositoryList(api: GitHubAPI): RepositoryList {
        return RetrofitRequestImpl(api)
    }

    @Singleton
    @Provides
    fun provideRepositoryDetailUser(api: GitHubAPI): RepositoryDetailUser {
        return RetrofitRequestImpl(api)
    }

    @Provides
    @Named("base_url")
    fun provideBaseUrl(): String {
        return "https://api.github.com"
    }

    @Provides
    @Named("username")
    fun provideDefaultUserName(): String {
        return "matr1x0"
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideAdapterFactory(): CallAdapter.Factory {
        return RxJava3CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        @Named("base_url") baseUrl: String,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converterFactory)
            .build()
    }
}