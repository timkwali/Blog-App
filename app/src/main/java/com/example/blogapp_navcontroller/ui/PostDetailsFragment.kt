package com.example.blogapp_navcontroller.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blogapp_navcontroller.R
import com.example.blogapp_navcontroller.adapter.CommentsRVAdapter
import com.example.blogapp_navcontroller.constants.Constants
import com.example.blogapp_navcontroller.requests.ApiRepository
import com.example.blogapp_navcontroller.room.data.Comment
import com.example.blogapp_navcontroller.room.viewmodel.RoomViewModel
import com.example.blogapp_navcontroller.viewModel.PostsViewModel
import com.example.blogapp_navcontroller.viewModel.PostsViewModelFactory
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.fragment_post_details.*


class PostDetailsFragment : Fragment() {
    lateinit var roomViewModel: RoomViewModel
    lateinit var postsViewModel: PostsViewModel
    lateinit var navController: NavController
    val args: PostDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /** SET POST IMAGE */
        Picasso.get().load("https://source.unsplash.com/random/150x150").into(post_image)

        /** INITIALIZE ROOM CLASSES */
        roomViewModel = ViewModelProvider(this).get(RoomViewModel::class.java)
        val postId = args.postId

        /** GET POSTS FROM ROOM VIEW-MODEL */
        val allPosts = roomViewModel.getPosts
        allPosts.observe(viewLifecycleOwner, Observer { posts ->
            /** SET POST TITLE AND BODY */
            for(post in posts) {
                if(post.id == postId) {
                    post_title.text = post.title
                    post_body.text = post.body
                }
            }
        })

        /** GET ROOM COMMENTS FROM ROOM-VIEW-MODEL */
        val allComments = roomViewModel.getComments
        val emptyList = emptyList<Comment>()
        val list = mutableListOf<Comment>()
        allComments.observe(viewLifecycleOwner, Observer { comments ->
            for(comment in comments) {
                if(comment.postId == postId) list.add(comment)
            }
            /** SET UP RECYCLER VIEW */
            val commentsRVAdapter = CommentsRVAdapter(list.reversed().toList())
            comments_recycler_view.adapter = commentsRVAdapter
            comments_recycler_view.layoutManager = LinearLayoutManager(this.context)
            comments_recycler_view.setHasFixedSize(true)
        })

        /** GET API COMMENTS FROM POSTS-VIEW-MODEL */
//        val pos = adapterPosition?.plus(1)
        val repository = ApiRepository()
        val postsViewModelFactory = PostsViewModelFactory(repository)
        postsViewModel = ViewModelProvider(this, postsViewModelFactory).get(PostsViewModel::class.java)
        postsViewModel.getComments(postId)

        postsViewModel.allComments.observe(viewLifecycleOwner, Observer { comments ->
            /** ADD COMMENT TO ROOM DATABASE */
            for(comment in comments.body()!!) {
                val currentComment = Comment(comment.id, postId, comment.name, comment.body)
                roomViewModel.addComment(currentComment)
            }
        })

        /** GO TO ADD COMMENT SCREEN */
        add_comment.setOnClickListener{
            val action = PostDetailsFragmentDirections.actionPostDetailsFragmentToNewCommentFragment(postId)
            navController.navigate(action)
        }
    }
}