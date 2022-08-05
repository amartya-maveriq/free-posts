package com.assignment.freeposts.data.network

import com.assignment.freeposts.data.models.Comment
import com.assignment.freeposts.data.models.Post
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Amartya Ganguly on 05/08/22.
 */
interface PostApiService {

    @GET("posts")
    suspend fun fetchPosts(): List<Post>

    @GET("comments")
    suspend fun fetchPostDetails(
        @Query("postId") id: Int
    ): List<Comment>
}