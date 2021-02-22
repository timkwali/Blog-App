package com.example.blogapp_navcontroller.room.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.android.synthetic.main.fragment_post_details.view.*

@Dao
interface PostDao {
    /** ADD POST TO DATABASE */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPost(post: Post)

    /** GET ALL POSTS FROM DATABASE */
    @Query("SELECT * FROM post_table ORDER BY id ASC")
    fun getAllPosts(): LiveData<List<Post>>

    /** SEARCH FOR POSTS */
    @Query("SELECT * FROM post_table WHERE title like :title")
    fun searchPosts(title: String): LiveData<List<Post>>
}