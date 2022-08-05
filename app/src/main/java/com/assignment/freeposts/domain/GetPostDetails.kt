package com.assignment.freeposts.domain

import com.assignment.freeposts.data.models.Comment
import com.assignment.freeposts.data.repository.PostRepository
import javax.inject.Inject


/**
 * Created by Amartya Ganguly on 05/08/22.
 */
class GetPostDetails @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(postId: Int): List<Comment> {
        return repository.fetchPostDetails(postId)
    }
}