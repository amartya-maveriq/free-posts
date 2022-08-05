package com.assignment.freeposts.utils

import com.assignment.freeposts.presentation.uistate.UiState
import kotlinx.coroutines.flow.MutableStateFlow


/**
 * Created by Amartya Ganguly on 06/08/22.
 */

fun MutableStateFlow<UiState>.reset() {
    this.value = UiState.Idle
}