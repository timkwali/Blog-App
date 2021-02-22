package com.example.blogapp_navcontroller.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.blogapp_navcontroller.R
import com.example.blogapp_navcontroller.room.data.Post
import com.example.blogapp_navcontroller.room.viewmodel.RoomViewModel
import kotlinx.android.synthetic.main.fragment_new_post.*
import kotlinx.android.synthetic.main.fragment_new_post.view.*
import kotlinx.android.synthetic.main.fragment_post_details.*


class NewPostFragment : Fragment() {

    private lateinit var roomViewModel: RoomViewModel
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_post, container, false)
//        navController = Navigation.findNavController(view)
//        roomViewModel = ViewModelProvider(this).get(RoomViewModel::class.java)
//
//        view.save_button.setOnClickListener {
//            /** CHECK IF FIELDS ARE EMPTY */
//            if(new_post_title.text.trim().isEmpty() || new_post_body.text.trim().isEmpty()) {
//                Toast.makeText(this.context, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
//            } else {
//                /** ADD DATA TO DATABASE */
//                sendPostToDatabase()
//
//            }
//        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        roomViewModel = ViewModelProvider(this).get(RoomViewModel::class.java)

        save_button.setOnClickListener{
            /** CHECK IF FIELDS ARE EMPTY */
            if(new_post_title.text.trim().isEmpty() || new_post_body.text.trim().isEmpty()) {
                Toast.makeText(this.context, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                /** ADD DATA TO DATABASE */
                sendPostToDatabase()
            }
        }

    }

    fun sendPostToDatabase() {
        val title = new_post_title.text.toString()
        val body = new_post_body.text.toString()
        val post = Post(0, title, body)

        /** ADD TO DATABASE */
        roomViewModel.addPost(post)
        Toast.makeText(this.context, "Post successfully added", Toast.LENGTH_SHORT).show()
        /** GO BACK TO POSTS */
//        fragmentManager?.popBackStack()
        navController.navigate(R.id.action_newPostFragment_to_postsFragment3)
    }
}