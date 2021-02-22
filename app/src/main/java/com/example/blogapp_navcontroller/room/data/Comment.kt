package com.example.blogapp_navcontroller.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/** CREATE TABLE COLUMNS FOR COMMENTS */
@Entity(tableName = "comment_table")
data class Comment(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val postId: Int,
    val name: String,
    val body: String
)