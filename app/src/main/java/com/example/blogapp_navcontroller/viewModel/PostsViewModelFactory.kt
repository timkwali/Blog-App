package com.example.blogapp_navcontroller.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.blogapp_navcontroller.requests.ApiRepository

class PostsViewModelFactory(private val apiRepository: ApiRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostsViewModel(apiRepository) as T
    }

}