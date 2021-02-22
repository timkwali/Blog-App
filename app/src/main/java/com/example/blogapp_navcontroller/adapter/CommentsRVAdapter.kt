package com.example.blogapp_navcontroller.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blogapp_navcontroller.R
import com.example.blogapp_navcontroller.room.data.Comment
import kotlinx.android.synthetic.main.comment_template.view.*

class CommentsRVAdapter(var commentsLists: List<Comment>): RecyclerView.Adapter<CommentsRVAdapter.CommentsViewHolder>() {

    /** INFLATE TEMPLATE ON RECYCLER VIEW */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.comment_template, parent, false)
        return CommentsViewHolder(itemView)
    }

    /** GET COMMENTS COUNT */
    override fun getItemCount(): Int {
        return commentsLists.size
    }

    /** BIND TEMPLATE VIEWS TO RECYCLER VIEW */
    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        val currentComment = commentsLists[position]
        holder.name.text = currentComment.name
        holder.comment.text = currentComment.body
    }

    /** VIEWHOLDER CLASS */
    inner class CommentsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.name
        val comment: TextView = itemView.comment
    }
}