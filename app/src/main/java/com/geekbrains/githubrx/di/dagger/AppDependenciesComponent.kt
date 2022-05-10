package com.geekbrains.githubrx.di.dagger

import com.geekbrains.githubrx.domain.RepositoryDetailUser
import com.geekbrains.githubrx.domain.RepositoryList
import com.geekbrains.githubrx.ui.main.main.FragmentMain
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModuleDagger::class
    ]
)

interface AppDependenciesComponent {

    fun inject(fragmentMain: FragmentMain) //указавыем куда нужно класть зависимости

    @Named("username")
    fun getDefaultUserName(): String
}
