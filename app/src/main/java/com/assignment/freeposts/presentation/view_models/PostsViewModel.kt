package com.assignment.freeposts.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val getPostDetails: GetPostDetails
): ViewModel() {

    val uiState = MutableStateFlow<UiState>(UiState.Idle)

    fun fetchPosts() {
        viewModelScope.launch {
            uiState.value = UiState.Loading
            runCatching {
                uiState.value = UiState.Success(getPosts())
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