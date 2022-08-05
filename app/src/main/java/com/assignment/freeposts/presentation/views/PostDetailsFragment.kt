package com.assignment.freeposts.presentation.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.assignment.freeposts.R
import com.assignment.freeposts.databinding.FragmentPostDetailsBinding


/**
 * Created by Amartya Ganguly on 05/08/22.
 */
class PostDetailsFragment: Fragment(R.layout.fragment_post_details) {

    private lateinit var binding: FragmentPostDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentPostDetailsBinding.bind(view)
    }
}