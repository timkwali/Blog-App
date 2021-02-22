package com.example.blogapp_navcontroller.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.blogapp_navcontroller.R
import com.example.blogapp_navcontroller.constants.Constants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {
    lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        name_text.text.clear()

        /** ENTER BLOG */
        enter_button.setOnClickListener{
            if(name_text.text.trim().toString().isEmpty()) {
                Toast.makeText(this.context, "Please enter your name to continue", Toast.LENGTH_SHORT).show()
            } else {
                /** GO TO POSTS FRAGMENT */
                val name = name_text.text.trim().toString()
                Constants.name = name
                /** CLOSE KEYBOARD */
                view?.hideKeyboard()
//                closeKeyboard()
                Toast.makeText(this.context, "Welcome ${Constants.name} to blogTR!", Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.action_homeFragment_to_postsFragment3)
            }
        }
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}