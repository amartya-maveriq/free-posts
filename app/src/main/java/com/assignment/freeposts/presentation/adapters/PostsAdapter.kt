package com.assignment.freeposts.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.freeposts.R
import com.assignment.freeposts.data.models.Post
import com.assignment.freeposts.databinding.ItemPostBinding
import com.assignment.freeposts.presentation.interfaces.PostClickListener
import com.assignment.freeposts.utils.clickWithDebounce


/**
 * Created by Amartya Ganguly on 06/08/22.
 */
class PostsAdapter(
    private val posts: MutableList<Post> = mutableListOf(),
    private val listener: PostClickListener
) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(posts: List<Post>) {
        this.posts.addAll(posts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(
            posts[position]
        )
    }

    override fun getItemCount(): Int {
        return this.posts.size
    }

    inner class PostsViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.tvTitle.text = post.title
            binding.tvAuthor.text =
                String.format(binding.root.context.getString(R.string.post_author), post.userId)
            binding.tvBody.text = post.body
            binding.root.clickWithDebounce {
                listener.onPostClicked(post)
            }
        }
    }
}