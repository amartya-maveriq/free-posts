package com.assignment.freeposts.data.repository

import com.assignment.freeposts.data.models.Comment
import com.assignment.freeposts.data.models.Post
import com.assignment.freeposts.data.network.PostApiService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * Created by Amartya Ganguly on 05/08/22.
 */
class PostRepositoryImpl @Inject constructor(
    private val postApiService: PostApiService
): PostRepository {
    override suspend fun fetchPosts(): List<Post> = withContext(IO) {
        postApiService.fetchPosts()
    }

    override suspend fun fetchPostDetails(postId: Int): List<Comment> = withContext(IO) {
        postApiService.fetchPostDetails(postId)
    }
}