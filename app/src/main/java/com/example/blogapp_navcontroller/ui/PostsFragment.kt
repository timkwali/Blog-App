package com.example.blogapp_navcontroller.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blogapp_navcontroller.R
import com.example.blogapp_navcontroller.adapter.OnPostClickListener
import com.example.blogapp_navcontroller.adapter.PostsRVAdapter
import com.example.blogapp_navcontroller.requests.ApiRepository
import com.example.blogapp_navcontroller.room.data.Post
import com.example.blogapp_navcontroller.room.viewmodel.RoomViewModel
import com.example.blogapp_navcontroller.viewModel.PostsViewModel
import com.example.blogapp_navcontroller.viewModel.PostsViewModelFactory
import kotlinx.android.synthetic.main.fragment_posts.*


val postDetailsFragment = PostDetailsFragment()

class PostsFragment : Fragment(), OnPostClickListener {

    lateinit var postsViewModel: PostsViewModel
    lateinit var roomViewModel: RoomViewModel
    lateinit var adapter: PostsRVAdapter
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /** INITIALIZE CLASSES */
        val newPostFragment = NewPostFragment()
        val repository = ApiRepository()
        val postsViewModelFactory = PostsViewModelFactory(repository)
        postsViewModel = ViewModelProvider(this, postsViewModelFactory).get(PostsViewModel::class.java)

        /** INITIALIZE ROOM VIEW MODEL */
        roomViewModel = ViewModelProvider(this).get(RoomViewModel::class.java)
        roomViewModel.getPosts.observe(viewLifecycleOwner, Observer { post ->
            /** SET UP RECYCLER VIEW */
            adapter = PostsRVAdapter(post.reversed(), this)
            posts_recycler_view.adapter = adapter
            posts_recycler_view.layoutManager = LinearLayoutManager(this.context)
            posts_recycler_view.setHasFixedSize(true)
        })

        /** GET API POSTS FROM POSTS-VIEW-MODEL */
        postsViewModel.getPosts()
        postsViewModel.allPosts.observe(viewLifecycleOwner, Observer { posts ->
            for(post in posts.body()!!) {
                /** ADD POST TO ROOM DATABASE */
                val currentPost = Post(post.id, post.title, post.body)
                roomViewModel.addPost(currentPost)
            }
        })

        /** GO TO ADD NEW POST ACTIVITY */
        floating_action_button.setOnClickListener {
            navController.navigate(R.id.action_postsFragment3_to_newPostFragment)
        }

        /** SEARCH POSTS */
        post_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.filter.filter(p0)
                return false
            }
        })
    }

    /** OVERRIDE ON-POST-CLICK */
    override fun onPostClick(position: Int, postId: Int) {
        /** HIDE KEYBOARD IF OPEN */
        view?.hideKeyboard()

        /** PASS POST-ID TO THE FRAGMENT */
        val action = PostsFragmentDirections.actionPostsFragment3ToPostDetailsFragment(postId)
        navController.navigate(action)
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}