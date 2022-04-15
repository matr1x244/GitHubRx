package com.geekbrains.githubrx.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.githubrx.domain.GitProjectEntity
import com.geekbrains.githubrx.domain.Repository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy


class MainViewModel(private val getRepository: Repository) : ViewModel() {

    private val _repos = MutableLiveData<List<GitProjectEntity>>()
    val repos: LiveData<List<GitProjectEntity>> = _repos

    private val _inProgress = MutableLiveData<Boolean>()
    val inProgress: LiveData<Boolean> = _inProgress

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun onShowList() {
        _inProgress.postValue(true)
        compositeDisposable.add(
            getRepository
                .observerReposListUser()
                .subscribeBy {
                    _inProgress.postValue(false)
                    _repos.postValue(it)
                }
        )
    }

    fun onShowRepository(username: String) {
        _inProgress.postValue(true)
        compositeDisposable.add(
            getRepository
                .observeReposForUser(username)
                .subscribeBy {
                    _inProgress.postValue(false)
                    _repos.postValue(it)
                }
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}

