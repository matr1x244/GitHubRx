package com.geekbrains.githubrx.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.githubrx.domain.GitProjectEntity
import com.geekbrains.githubrx.domain.Repository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy


class MainViewModel(private val getRepository: Repository) : ViewModel() {

    private val _repos = MutableLiveData<List<GitProjectEntity>>() // закидываем событие
    val repos: LiveData<List<GitProjectEntity>> = _repos // читаем событие

    private val compositeDisposable: CompositeDisposable = CompositeDisposable() // метод отписки RX

    fun onShowList() {
        compositeDisposable.add(
            getRepository
                .observerReposListUser()
                .subscribeBy {
                    _repos.postValue(it)
                }
        )
    }

    fun onShowRepository(username: String) {
        compositeDisposable.add(
            getRepository
                .observeReposForUser(username)
                .subscribeBy {
                    _repos.postValue(it)
                }
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}

