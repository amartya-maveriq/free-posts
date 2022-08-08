package com.assignment.freeposts.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignment.freeposts.data.models.Post


/**
 * Created by Amartya Ganguly on 06/08/22.
 */

@Database(entities = [Post::class], version = 1, exportSchema = true)
abstract class AppDb: RoomDatabase() {

    abstract fun getPostsDao(): PostsDao
}