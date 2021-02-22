package com.example.blogapp_navcontroller.room.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CommentDao {
    /** ADD COMMENT TO DATABASE */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addComment(comment: Comment)

    /** GET ALL COMMENTS FROM DATABASE */
    @Query("SELECT * FROM comment_table ORDER BY id ASC")
    fun getAllComments(): LiveData<List<Comment>>
}