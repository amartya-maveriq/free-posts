package com.assignment.freeposts.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.freeposts.R
import com.assignment.freeposts.data.models.Post
import com.assignment.freeposts.databinding.ItemCommentHeaderBinding


/**
 * Created by Amartya Ganguly on 06/08/22.
 */
class PostHeaderAdapter(
    private val post: Post
): RecyclerView.Adapter<PostHeaderAdapter.PostHeaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHeaderViewHolder {
        return PostHeaderViewHolder(
            ItemCommentHeaderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PostHeaderViewHolder, position: Int) {
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return 1
    }

    inner class PostHeaderViewHolder(
        private val binding: ItemCommentHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.tvTitle.text = post.title
            binding.tvAuthor.text =  String.format(binding.root.context.getString(R.string.post_author), post.userId)
            binding.tvBody.text = post.body
        }
    }
}