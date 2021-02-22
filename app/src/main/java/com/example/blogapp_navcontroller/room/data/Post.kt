package com.example.blogapp_navcontroller.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/** CREATE TABLE COLUMNS FOR POSTS */
@Entity(tableName = "post_table")
data class Post(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val body: String
)