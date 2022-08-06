package com.assignment.freeposts.presentation.interfaces

import com.assignment.freeposts.data.models.Post


/**
 * Created by Amartya Ganguly on 06/08/22.
 */
interface PostClickListener {
    fun onPostClicked(post: Post)
}