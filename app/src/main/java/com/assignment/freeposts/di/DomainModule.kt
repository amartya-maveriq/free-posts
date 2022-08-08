package com.assignment.freeposts.di

import com.assignment.freeposts.data.repository.PostRepository
import com.assignment.freeposts.domain.FetchLatestPosts
import com.assignment.freeposts.domain.GetPostDetails
import com.assignment.freeposts.domain.GetPosts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by Amartya Ganguly on 05/08/22.
 */

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun providePosts(repository: PostRepository) =
        GetPosts(repository)

    @Provides
    @Singleton
    fun providePostDetails(repository: PostRepository) =
        GetPostDetails(repository)

    @Provides
    @Singleton
    fun provideFetchPosts(repository: PostRepository) =
        FetchLatestPosts(repository)
}