package com.geekbrains.githubrx

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.geekbrains.githubrx.di.dagger.AppDependenciesComponent
import com.geekbrains.githubrx.di.dagger.AppModuleDagger
import com.geekbrains.githubrx.di.dagger.DaggerAppDependenciesComponent
import com.geekbrains.githubrx.di.koin.appModuleKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    /*dagger*/
    lateinit var appDependenciesComponent: AppDependenciesComponent

    override fun onCreate() {
        super.onCreate()

        appDependenciesComponent = DaggerAppDependenciesComponent
            .builder()
            .appModuleDagger(AppModuleDagger(app))
            .build()

    }

}

val Context.app: App
    get() = applicationContext as App

val Fragment.app: App
    get() = requireActivity().app
