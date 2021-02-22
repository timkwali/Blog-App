package com.example.blogapp_navcontroller.room.data

import androidx.lifecycle.LiveData

class Repository(private val postDao: PostDao, private val commentDao: CommentDao) {
    /** GET ALL POSTS */
    val getAllPosts: LiveData<List<Post>> = postDao.getAllPosts()

    /** ADD POST TO DATABASE */
    suspend fun addPost(post: Post) {
        postDao.addPost(post)
    }

    /** GET ALL COMMENTS */
    val getAllComments: LiveData<List<Comment>> = commentDao.getAllComments()

    /** ADD COMMENTS TO DATABASE */
    suspend fun addComment(comment: Comment) {
        commentDao.addComment(comment)
    }
}