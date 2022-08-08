package com.assignment.freeposts.di

import android.content.Context
import androidx.room.Room
import com.assignment.freeposts.data.db.AppDb
import com.assignment.freeposts.data.db.PostsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by Amartya Ganguly on 06/08/22.
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DB_NAME = "com.assignment.freeposts.database"

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): AppDb =
        Room.databaseBuilder(context, AppDb::class.java, DB_NAME).build()

    @Singleton
    @Provides
    fun provideDao(db: AppDb): PostsDao = db.getPostsDao()
}