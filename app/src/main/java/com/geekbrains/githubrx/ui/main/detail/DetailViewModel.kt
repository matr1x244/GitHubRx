package com.geekbrains.githubrx.ui.main.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekbrains.githubrx.domain.GitProjectUserDetail
import com.geekbrains.githubrx.domain.RepositoryDetailUser
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val getDetailUser: RepositoryDetailUser) : ViewModel() {

    private val _repos = MutableLiveData<GitProjectUserDetail>() // закидываем событие
    val repos: LiveData<GitProjectUserDetail> = _repos // читаем событие

    private val compositeDisposable: CompositeDisposable = CompositeDisposable() // метод отписки RX

//    fun onShowLogin(username: String?) {
//        compositeDisposable.add(
//            getDetailUser
//                .observerUserDetail(username.toString())
//                .subscribeBy(
//                    onSuccess = {
//                        _repos.postValue(it)
//                    },
//                    onError = {
//                        //....
//                    }
//                )
//        )
//    }

    fun onShowLogin(username: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getDetailUser.observerUserDetail(username.toString())
            withContext(Dispatchers.Main) {
                _repos.postValue(result)
            }
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}