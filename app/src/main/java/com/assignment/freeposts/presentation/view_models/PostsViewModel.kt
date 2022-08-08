package com.assignment.freeposts.presentation.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.freeposts.domain.FetchLatestPosts
import com.assignment.freeposts.domain.GetPostDetails
import com.assignment.freeposts.domain.GetPosts
import com.assignment.freeposts.presentation.uistate.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Amartya Ganguly on 05/08/22.
 */

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPosts: GetPosts,
    private val getPostDetails: GetPostDetails,
    private val fetchLatest: FetchLatestPosts
) : ViewModel() {

    val uiState = MutableStateFlow<UiState>(UiState.Idle)

    fun fetchPosts() {
        viewModelScope.launch {
            uiState.value = UiState.Loading
            runCatching {
                uiState.value = UiState.Success(getPosts())
                Log.d("TAG", "fetchPosts: Got some posts from db")
                fetchLatestPosts()
            }.onFailure {
                uiState.value = UiState.Error(it.cause)
            }
        }
    }

    private fun fetchLatestPosts() {
        viewModelScope.launch {
            runCatching {
                uiState.value = UiState.Success(fetchLatest())
                Log.d("TAG", "fetchPosts: Got some posts from nw")
            }.onFailure {
                uiState.value = UiState.Error(it.cause)
            }
        }
    }

    fun fetchPostDetails(postId: Int) {
        viewModelScope.launch {
            uiState.value = UiState.Loading
            runCatching {
                uiState.value = UiState.Success(getPostDetails(postId))
            }.onFailure {
                uiState.value = UiState.Error(it.cause)
            }
        }
    }
}