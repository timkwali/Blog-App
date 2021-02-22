package com.example.blogapp_navcontroller.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.blogapp_navcontroller.data.CommentsData
import com.example.blogapp_navcontroller.data.PostData
import com.example.blogapp_navcontroller.requests.ApiRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class PostsViewModel(private val apiRepository: ApiRepository): ViewModel() {

    var allPosts: MutableLiveData<Response<List<PostData>>> = MutableLiveData()
    var allComments: MutableLiveData<Response<List<CommentsData>>> = MutableLiveData()


    /** GET ALL POSTS */
    fun getPosts() {
        viewModelScope.launch {
            val response = apiRepository.getPosts()
            allPosts.value = response
        }
    }

    /** GET ALL COMMENTS */
    fun getComments(postId: Int) {
        viewModelScope.launch {
            val response = apiRepository.getComments(postId)
            allComments.value = response
        }
    }
}