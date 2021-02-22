package com.example.blogapp_navcontroller.data


import com.google.gson.annotations.SerializedName

data class CommentsData(
    @SerializedName("body")
    val body: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("postID")
    val postID: Int
)