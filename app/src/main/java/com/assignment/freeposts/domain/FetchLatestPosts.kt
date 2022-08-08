package com.assignment.freeposts.domain

import com.assignment.freeposts.data.models.Post
import com.assignment.freeposts.data.repository.PostRepository
import javax.inject.Inject


/**
 * Created by Amartya Ganguly on 07/08/22.
 */
class FetchLatestPosts @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(): List<Post> =
        repository.fetchPosts()
}