package com.assignment.freeposts.domain

import com.assignment.freeposts.data.models.Post
import com.assignment.freeposts.data.repository.PostRepository
import javax.inject.Inject


/**
 * Created by Amartya Ganguly on 09/08/22.
 */
class SearchPost @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(searchTerm: String) : List<Post> {
        return repository.getPosts(searchTerm)
    }
}