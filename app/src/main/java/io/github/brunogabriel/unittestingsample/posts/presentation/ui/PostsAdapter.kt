package io.github.brunogabriel.unittestingsample.posts.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.brunogabriel.unittestingsample.R
import io.github.brunogabriel.unittestingsample.databinding.ViewPostBinding
import io.github.brunogabriel.unittestingsample.posts.domain.models.Post

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
    private val items = mutableListOf<Post>()

    fun addAll(newItems: List<Post>) {
        val (start, numberOfNewItems) = Pair(items.size, newItems.size)
        items.addAll(newItems)
        notifyItemRangeInserted(start, numberOfNewItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ViewPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class ViewHolder(
        private val binding: ViewPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) = with(binding) {
            identifierText.text = itemView.context.getString(R.string.identifier_pattern, post.id)
            userIdentifierText.text =
                itemView.context.getString(R.string.user_identifier_pattern, post.userId)
            titleText.text = itemView.context.getString(R.string.title_pattern, post.title)
            bodyText.text = itemView.context.getString(R.string.body_pattern, post.body)
        }
    }

}