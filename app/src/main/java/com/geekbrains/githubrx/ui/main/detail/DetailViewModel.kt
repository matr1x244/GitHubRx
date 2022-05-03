package com.geekbrains.githubrx.ui.main.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.githubrx.domain.GitProjectUserDetail
import com.geekbrains.githubrx.domain.RepositoryDetailUser
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class DetailViewModel(private val getDetailUser: RepositoryDetailUser) : ViewModel() {

    private val _repos = MutableLiveData<List<GitProjectUserDetail>>() // закидываем событие
    val repos: LiveData<List<GitProjectUserDetail>> = _repos // читаем событие

    private val compositeDisposable: CompositeDisposable = CompositeDisposable() // метод отписки RX

    fun onShowLogin(username: String) {
        compositeDisposable.add(
            getDetailUser
                .observerUserDetail(username)
                .subscribeBy(
                    onSuccess = {
                        _repos.postValue(it)
                    },
                    onError = {
                        //....
                    }
                )
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}