package com.example.blogapp_navcontroller.data


import com.google.gson.annotations.SerializedName

data class PostData(
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("userId")
    val userId: Int
)