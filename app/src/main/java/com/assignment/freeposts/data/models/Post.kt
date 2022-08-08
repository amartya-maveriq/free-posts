package com.assignment.freeposts.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Post(
    @PrimaryKey val id: Int = 0,
    val body: String = "",
    val title: String = "",
    val userId: Int = 0
): Parcelable