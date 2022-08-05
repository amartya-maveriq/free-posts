package com.assignment.freeposts.presentation.uistate


/**
 * Created by Amartya Ganguly on 05/08/22.
 */
sealed class UiState {

    data class Success(
        val result: Any?
    ) : UiState()

    data class Error(
        val exception: Throwable?
    ) : UiState()

    object Idle : UiState()
    object Loading : UiState()
}
