package com.example.blogapp_navcontroller.requests

import com.example.blogapp_navcontroller.data.CommentsData
import com.example.blogapp_navcontroller.data.PostData
import retrofit2.Response

class ApiRepository {
    /** GET ALL POSTS */
    suspend fun getPosts(): Response<List<PostData>> {
        return RetrofitInstance.getPostsApi().getPosts()
    }

    suspend fun getComments(postId: Int): Response<List<CommentsData>> {
        return RetrofitInstance.getPostsApi().getComments(postId)
    }
}