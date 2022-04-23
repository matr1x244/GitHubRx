package com.geekbrains.githubrx.ui.main.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.githubrx.domain.Repository

class DetailViewModelFactory(private val repo: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(repo) as T
    }
}