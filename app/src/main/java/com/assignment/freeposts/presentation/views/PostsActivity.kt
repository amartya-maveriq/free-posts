package com.assignment.freeposts.presentation.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assignment.freeposts.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}