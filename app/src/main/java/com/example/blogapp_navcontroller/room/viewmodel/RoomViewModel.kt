package com.example.blogapp_navcontroller.room.viewmodel

import android.app.Application
import android.icu.text.CaseMap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.blogapp_navcontroller.room.data.Comment
import com.example.blogapp_navcontroller.room.data.Post
import com.example.blogapp_navcontroller.room.data.PostDatabase
import com.example.blogapp_navcontroller.room.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomViewModel(application: Application): AndroidViewModel(application) {
    var getPosts: LiveData<List<Post>>
    val getComments: LiveData<List<Comment>>
    private val postRepository: Repository

    /** GET POSTS AND COMMENTS FROM DATABASE */
    init {
        val commentDao = PostDatabase.getDatabase(application).commentDao()
        val postDao = PostDatabase.getDatabase(application).postDao()
        postRepository = Repository(postDao, commentDao)
        getPosts = postRepository.getAllPosts
        getComments = postRepository.getAllComments
    }

    /** ADD POST TO DATABASE */
    fun addPost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            postRepository.addPost(post)
        }
    }

    /** ADD COMMENT TO DATABASE */
    fun addComment(comment: Comment) {
        viewModelScope.launch(Dispatchers.IO){
            postRepository.addComment(comment)
        }
    }
}