package com.assignment.freeposts.data.repository

import com.assignment.freeposts.data.models.Comment
import com.assignment.freeposts.data.models.Post


/**
 * Created by Amartya Ganguly on 05/08/22.
 */
interface PostRepository {

    suspend fun fetchPosts(): List<Post>

    suspend fun fetchPostDetails(postId: Int): List<Comment>
}