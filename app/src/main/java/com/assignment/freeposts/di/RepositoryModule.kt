package com.assignment.freeposts.di

import com.assignment.freeposts.data.network.PostApiService
import com.assignment.freeposts.data.repository.PostRepository
import com.assignment.freeposts.data.repository.PostRepositoryImpl
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
object RepositoryModule {

    @Provides
    @Singleton
    fun providePostRepo(apiService: PostApiService): PostRepository =
        PostRepositoryImpl(apiService)
}