package com.assignment.freeposts.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.freeposts.data.models.Post


/**
 * Created by Amartya Ganguly on 06/08/22.
 */
@Dao
interface PostsDao {
    @Query("SELECT * FROM Post")
    fun getAll(): List<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: Post)

    @Query("DELETE FROM Post")
    fun deleteAll()
}