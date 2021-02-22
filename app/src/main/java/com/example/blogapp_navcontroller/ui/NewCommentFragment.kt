package com.example.blogapp_navcontroller.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.blogapp_navcontroller.R
import com.example.blogapp_navcontroller.constants.Constants
import com.example.blogapp_navcontroller.room.data.Comment
import com.example.blogapp_navcontroller.room.viewmodel.RoomViewModel
import kotlinx.android.synthetic.main.fragment_new_comment.*
import kotlinx.android.synthetic.main.fragment_post_details.*


class NewCommentFragment : Fragment() {
    lateinit var roomViewModel: RoomViewModel
    lateinit var navController: NavController
    val args: NewCommentFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_comment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        roomViewModel = ViewModelProvider(this).get(RoomViewModel::class.java)
        val postId = args.postId

        save_button.setOnClickListener{
            /** CHECK IF FIELDS ARE EMPTY */
            if(comment_box.text.trim().isEmpty()) {
                Toast.makeText(this.context, "Please add a comment first", Toast.LENGTH_SHORT).show()
            } else {
                /** ADD DATA TO DATABASE */
                sendCommentToDatabase(postId!!)
            }
        }
    }

    private fun sendCommentToDatabase(postId: Int) {
        val commentText = comment_box.text.trim().toString()
        val name = Constants.name!!
        val comment = Comment(0, postId, name, commentText)
        /** ADD COMMENT TO DATABASE */
        roomViewModel.addComment(comment)
        Toast.makeText(this.context, "Comment Successfully Added", Toast.LENGTH_SHORT).show()
        /** HIDE KEYBOARD */
        view?.hideKeyboard()
        /** GO BACK TO POST DETAILS */
        val action = NewCommentFragmentDirections.actionNewCommentFragmentToPostDetailsFragment(postId)
        navController.navigate(action)
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}