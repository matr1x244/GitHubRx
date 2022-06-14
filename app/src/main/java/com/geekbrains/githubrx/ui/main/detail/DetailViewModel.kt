package com.geekbrains.githubrx.ui.main.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekbrains.githubrx.domain.GitProjectUserDetail
import com.geekbrains.githubrx.domain.RepositoryDetailUser
import kotlinx.coroutines.*

class DetailViewModel(private val getDetailUser: RepositoryDetailUser) : ViewModel() {

    private val _repos = MutableLiveData<GitProjectUserDetail>() // закидываем событие
    val repos: LiveData<GitProjectUserDetail> = _repos // читаем событие

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable -> // обрабатываем Exception в корутинах
            Log.v("@@@", "No success $throwable")
        }

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
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler + SupervisorJob()) {
            val result = getDetailUser.observerUserDetail(username.toString())
            withContext(Dispatchers.Main) {
                _repos.postValue(result)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}