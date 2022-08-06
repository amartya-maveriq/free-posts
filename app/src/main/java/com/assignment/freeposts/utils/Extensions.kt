package com.assignment.freeposts.utils

import android.os.SystemClock
import android.view.View
import com.assignment.freeposts.presentation.uistate.UiState
import kotlinx.coroutines.flow.MutableStateFlow


/**
 * Created by Amartya Ganguly on 06/08/22.
 */

/**
 * Prevent any view to be clicked twice accidentally withing one second
 */
fun View.clickWithDebounce(debounceTime: Long = 1000L, action: (View) -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action(v)

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun MutableStateFlow<UiState>.reset() {
    this.value = UiState.Idle
}