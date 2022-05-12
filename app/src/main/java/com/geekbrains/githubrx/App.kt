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
        /**
         * KOIN
         */
//        startKoin {
//            androidLogger()
//            androidContext(this@App)
//            modules(appModuleKoin)
//        }
    }


//    /**
//     * Обычный способ без использования Koin
//     * создаём retrofit за пределами класса RetrofitRequestImpl
//     */
//    private val apiUrl = "https://api.github.com"
//    private val jsonConverterFactory by lazy { GsonConverterFactory.create() }
//    private val rxJava3CallAdapterFactory by lazy { RxJava3CallAdapterFactory.create() }
//
//    private val retrofit by lazy {
//        Retrofit.Builder()
//            .baseUrl(apiUrl)
//            .addCallAdapterFactory(rxJava3CallAdapterFactory)
//            .addConverterFactory(jsonConverterFactory)
//            .build()
//    }
//
//    private val gitHubAPI: GitHubAPI = retrofit.create(GitHubAPI::class.java)
//
//    /**
//     * подключаемcя к retrofit как либо запросы
//     */
//    val getHubListUser: RepositoryList by lazy { RetrofitRequestImpl(gitHubAPI) }
//
//    val getHubDetailUser: RepositoryDetailUser by lazy { RetrofitRequestImpl(gitHubAPI) }
}

val Context.app: App
    get() = applicationContext as App

val Fragment.app: App
    get() = requireActivity().app
