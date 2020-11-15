package com.example.assignment.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment.repository.NewsRepository
import com.example.assignment.ui.NewsViewModel.NewsViewModel

class NewsViewModelProviderFactory(
        val app: Application,
    val newsRepository: NewsRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(app, newsRepository ) as T
    }
}