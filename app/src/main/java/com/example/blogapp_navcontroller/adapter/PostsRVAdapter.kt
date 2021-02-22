package com.example.blogapp_navcontroller.adapter

import com.example.blogapp_navcontroller.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blogapp_navcontroller.room.data.Post
import com.example.blogapp_navcontroller.room.viewmodel.RoomViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_post_details.*
import kotlinx.android.synthetic.main.posts_template.view.*
import java.security.acl.Owner
import java.util.*

class PostsRVAdapter(private val postsList: List<Post>, var clickListener: OnPostClickListener):
    RecyclerView.Adapter<PostsRVAdapter.PostsViewHolder>(), Filterable {

    /** FILTER RECYCLER VIEW FOR SEARCH */
    var postsFilterList = listOf<Post>()
    init {
        postsFilterList = postsList
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charSearch = p0.toString()
                if(charSearch.isEmpty()) {
                    postsFilterList = postsList
                } else {
                    val resultList = mutableListOf<Post>()
                    for(row in postsList) {
                        if(row.title.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    postsFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = postsFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                postsFilterList = p1?.values as List<Post>
                notifyDataSetChanged()
            }
        }
    }

    /** INFLATE TEMPLATE ON RECYCLER VIEW */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.posts_template, parent, false)
        return PostsViewHolder(itemView)
    }

    /** GET POSTS COUNT */
    override fun getItemCount(): Int {
        return postsFilterList.size
    }

    /** BIND TEMPLATE VIEWS TO RECYCLER VIEW */
    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val currentPost = postsFilterList[position]
        holder.postHeading.text = currentPost.title
        holder.postBody.text = currentPost.body

        /** LOAD POST IMAGE */
        Picasso.get().load("https://source.unsplash.com/random/150x150").into(holder.postImage)

        /** GET NUMBER OF COMMENTS */
//        val roomViewModel = ViewModelProvider(ViewModelStoreOwner).get(RoomViewModel::class.java)
//        val allComments = roomViewModel.getComments
//        var numberOfComments = 0
//        allComments.observe(this, Observer { comments ->
//            for(comment in comments) {
//                if(comment.postId == currentPost.id) numberOfComments++
//            }
//            holder.comments.text = numberOfComments.toString()
//        })
    }

    /** VIEW HOLDER CLASS */
    inner class PostsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val postHeading: TextView = itemView.post_heading
        val postBody: TextView = itemView.post_body
        val postImage: ImageView = itemView.post_image
        val comments: TextView = itemView.number_of_comments

        init {
            itemView.setOnClickListener {
                val pos = adapterPosition
                val currentPost = postsList[pos]
                val postId = currentPost.id
                if(pos != RecyclerView.NO_POSITION) {
                    clickListener.onPostClick(pos, postId)
                }
            }
        }
    }


    /** ADD LISTENER FOR POST CLICK */
    fun setOnPostClickListener(listener: OnPostClickListener) {
        clickListener = listener
    }
}

/** CLICK INTERFACE */
interface OnPostClickListener {
    fun onPostClick(position: Int, postId: Int)
}