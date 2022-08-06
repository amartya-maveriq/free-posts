package com.assignment.freeposts.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.freeposts.data.models.Comment
import com.assignment.freeposts.databinding.ItemCommentBinding


/**
 * Created by Amartya Ganguly on 06/08/22.
 */
class CommentsAdapter(
    private val comments: MutableList<Comment> = mutableListOf()
): RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(comments: List<Comment>) {
        this.comments.apply {
            clear()
            addAll(comments)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        return CommentsViewHolder(
            ItemCommentBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    inner class CommentsViewHolder(private val binding: ItemCommentBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment) {
            binding.tvName.text = comment.name
            binding.tvEmail.text = comment.email
            binding.tvComment.text = comment.body
        }
    }
}