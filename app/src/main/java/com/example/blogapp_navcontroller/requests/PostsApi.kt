package com.example.blogapp_navcontroller.requests


import com.example.blogapp_navcontroller.data.CommentsData
import com.example.blogapp_navcontroller.data.PostData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface PostsApi {

    @GET("posts")
    suspend fun getPosts(): Response<List<PostData>>

    @GET("posts/{id}/comments")
    suspend fun getComments(@Path("id") id : Int): Response<List<CommentsData>>
}