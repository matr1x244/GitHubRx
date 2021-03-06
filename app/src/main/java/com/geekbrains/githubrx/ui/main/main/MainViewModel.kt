package com.geekbrains.githubrx.ui.main.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.githubrx.domain.GitProjectEntity
import com.geekbrains.githubrx.domain.RepositoryList
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy


class MainViewModel(private val getRepositoryList: RepositoryList) : ViewModel() {

    private val _repos = MutableLiveData<List<GitProjectEntity>>() // закидываем событие
    val repos: LiveData<List<GitProjectEntity>> = _repos // читаем событие

    private val _inProgress = MutableLiveData<Boolean>() // закидываем событие
    val inProgress: LiveData<Boolean> = _inProgress // читаем событие

    private val compositeDisposable: CompositeDisposable = CompositeDisposable() // метод отписки RX

    fun onShowList() {
        _inProgress.postValue(true)
        compositeDisposable.add(
            getRepositoryList
                .observerReposListUser()
                .subscribeBy(
                    onSuccess = {
                        _inProgress.postValue(false)
                        _repos.postValue(it)
                    },
                    onError = {
                        _inProgress.postValue(false)
                    }
                )
        )
    }

    fun onShowRepository(username: String) {
        _inProgress.postValue(true)
        compositeDisposable.add(
            getRepositoryList
                .observeReposForUser(username)
                .subscribeBy(
                    onSuccess = {
                        _inProgress.postValue(false)
                        _repos.postValue(it)
                    },
                    onError = {
                        _inProgress.postValue(false)
                    }
                )
        )
    }

    interface Controller {
        fun openDetailFragment(gitProjectEntity: GitProjectEntity) {
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}

