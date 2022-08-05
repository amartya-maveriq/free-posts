package com.assignment.freeposts.presentation.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.assignment.freeposts.R
import com.assignment.freeposts.databinding.ActivityPostsBinding
import com.assignment.freeposts.presentation.uistate.UiState
import com.assignment.freeposts.presentation.view_models.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostsBinding
    private val viewModel by viewModels<PostsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.posts.collectLatest {
                    when (it) {
                        is UiState.Idle -> binding.progressBar.isVisible = false
                        is UiState.Loading -> binding.progressBar.isVisible = true
                        else -> Unit
                    }
                }
            }
        }
    }
}