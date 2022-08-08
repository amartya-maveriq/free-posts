package com.assignment.freeposts.presentation.views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.freeposts.R
import com.assignment.freeposts.data.models.Post
import com.assignment.freeposts.databinding.FragmentShowPostsBinding
import com.assignment.freeposts.presentation.adapters.PostsAdapter
import com.assignment.freeposts.presentation.interfaces.PostClickListener
import com.assignment.freeposts.presentation.uistate.UiState
import com.assignment.freeposts.presentation.view_models.PostsViewModel
import com.assignment.freeposts.utils.reset
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


/**
 * Created by Amartya Ganguly on 05/08/22.
 */
@AndroidEntryPoint
class ShowPostsFragment : Fragment(R.layout.fragment_show_posts), PostClickListener {

    private lateinit var binding: FragmentShowPostsBinding
    private val viewModel: PostsViewModel by activityViewModels()
    private val adapter: PostsAdapter by lazy { PostsAdapter(listener = this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentShowPostsBinding.bind(view)
        binding.toolbar.title = getString(R.string.app_name)
        binding.rvPosts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ShowPostsFragment.adapter
        }
        viewModel.fetchPosts()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            (it.result as List<*>).filterIsInstance<Post>().also { posts ->
                                if (posts.isNotEmpty()) {
                                    adapter.setItems(posts)
                                } else {
                                    Toast.makeText(
                                        requireContext(), "No posts to display", Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                        is UiState.Error -> {
                            Toast.makeText(
                                requireContext(), it.exception?.message, Toast.LENGTH_SHORT
                            ).show()
                        }
                        else -> Unit
                    }
                    viewModel.uiState.reset()
                }
            }
        }
    }

    override fun onPostClicked(post: Post) {
        requireView().findNavController()
            .navigate(ShowPostsFragmentDirections.actionShowPostsFragmentToPostDetailsFragment(post))
    }
}