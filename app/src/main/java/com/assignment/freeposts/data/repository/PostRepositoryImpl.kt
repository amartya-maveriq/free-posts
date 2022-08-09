package com.assignment.freeposts.data.repository

import com.assignment.freeposts.data.db.PostsDao
import com.assignment.freeposts.data.models.Comment
import com.assignment.freeposts.data.models.Post
import com.assignment.freeposts.data.network.PostApiService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * Created by Amartya Ganguly on 05/08/22.
 */
class PostRepositoryImpl @Inject constructor(
    private val postApiService: PostApiService,
    private val postsDao: PostsDao
): PostRepository {
    override suspend fun fetchPosts(): List<Post> = withContext(IO) {
        val posts = postApiService.fetchPosts()
        launch { updatePosts(posts) }
        posts
    }

    private suspend fun updatePosts(posts: List<Post>) = withContext(IO) {
        postsDao.deleteAll()
        for (post in posts) {
            postsDao.insert(post)
        }
    }

    override suspend fun getPosts(): List<Post> = withContext(IO) {
        postsDao.getAll()
    }

    override suspend fun getPosts(searchTerm: String): List<Post> = withContext(IO) {
        postsDao.searchPosts(searchTerm)
    }

    override suspend fun fetchPostDetails(postId: Int): List<Comment> = withContext(IO) {
        postApiService.fetchPostDetails(postId)
    }
}