package com.geekbrains.githubrx.ui.main.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.githubrx.domain.RepositoryList

class MainViewModelFactory(private val repo: RepositoryList) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repo) as T
    }
}