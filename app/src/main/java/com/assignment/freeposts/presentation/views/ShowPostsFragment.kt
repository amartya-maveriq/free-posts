package com.assignment.freeposts.presentation.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.assignment.freeposts.R
import com.assignment.freeposts.databinding.FragmentShowPostsBinding


/**
 * Created by Amartya Ganguly on 05/08/22.
 */
class ShowPostsFragment: Fragment(R.layout.fragment_show_posts) {

    private lateinit var binding: FragmentShowPostsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentShowPostsBinding.bind(view)
    }
}