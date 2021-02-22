package com.example.blogapp_navcontroller.requests

import com.example.blogapp_navcontroller.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    /** RETROFIT BUILDER */
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val  postApi: PostsApi by lazy {
        retrofit.create(PostsApi::class.java)
    }


    fun getPostsApi(): PostsApi {
        return postApi
    }
}