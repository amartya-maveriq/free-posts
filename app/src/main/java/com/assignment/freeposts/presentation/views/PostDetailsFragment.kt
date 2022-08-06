package com.assignment.freeposts.presentation.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.freeposts.R
import com.assignment.freeposts.data.models.Comment
import com.assignment.freeposts.databinding.FragmentPostDetailsBinding
import com.assignment.freeposts.presentation.adapters.CommentsAdapter
import com.assignment.freeposts.presentation.adapters.PostHeaderAdapter
import com.assignment.freeposts.presentation.uistate.UiState
import com.assignment.freeposts.presentation.view_models.PostsViewModel
import com.assignment.freeposts.utils.reset
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * Created by Amartya Ganguly on 05/08/22.
 */
class PostDetailsFragment: Fragment(R.layout.fragment_post_details) {

    private lateinit var binding: FragmentPostDetailsBinding
    private val args by navArgs<PostDetailsFragmentArgs>()
    private val viewModel by activityViewModels<PostsViewModel>()
    private val commentsAdapter by lazy { CommentsAdapter() }
    private val headerAdapter by lazy { PostHeaderAdapter(args.post) }
    private val adapter: ConcatAdapter by lazy { ConcatAdapter(headerAdapter, commentsAdapter) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentPostDetailsBinding.bind(view)

        binding.rvComments.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@PostDetailsFragment.adapter
        }

        viewModel.fetchPostDetails(postId = args.post.id)
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest {
                    when (it) {
                        is UiState.Success -> {
                            (it.result as List<*>).filterIsInstance<Comment>().also { comments ->
                                if (comments.isNotEmpty()) {
                                    commentsAdapter.setItems(comments)
                                } else {
                                    // todo show no comments
                                }
                            }
                        }
                        is UiState.Error -> {
                            println(it.exception?.message)
                        }
                        else -> Unit
                    }
                    viewModel.uiState.reset()
                }
            }
        }
    }
}