package com.example.blogapp_navcontroller.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blogapp_navcontroller.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()

        /** LOAD POSTS FRAGMENT */
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, homeFragment)
                .commit()
        }
    }
}