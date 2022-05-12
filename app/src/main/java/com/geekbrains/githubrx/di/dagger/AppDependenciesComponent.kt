package com.geekbrains.githubrx.di.dagger

import com.geekbrains.githubrx.ui.main.detail.DetailFragment
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

    fun injectMain(fragmentMain: FragmentMain) //указавыем куда нужно класть зависимости
    fun injectDetail(fragmentDetail: DetailFragment)

    @Named("username")
    fun getDefaultUserName(): String
}
