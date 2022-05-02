package com.geekbrains.githubrx.ui.main.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.githubrx.domain.RepositoryDetailUser

class DetailViewModelFactory(private val detailUser: RepositoryDetailUser) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(detailUser) as T
    }
}