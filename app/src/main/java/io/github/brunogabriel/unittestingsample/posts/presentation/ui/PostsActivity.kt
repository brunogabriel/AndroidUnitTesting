package io.github.brunogabriel.unittestingsample.posts.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.github.brunogabriel.unittestingsample.R
import io.github.brunogabriel.unittestingsample.databinding.ActivityPostsBinding
import io.github.brunogabriel.unittestingsample.posts.presentation.viewmodel.PostsViewModel
import io.github.brunogabriel.unittestingsample.shared.databinding.bind
import io.github.brunogabriel.unittestingsample.shared.extensions.dp
import io.github.brunogabriel.unittestingsample.shared.view.MarginItemDecoration

@AndroidEntryPoint
class PostsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostsBinding
    private val postsViewModel: PostsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bind(R.layout.activity_posts) {
            vm = postsViewModel
            lifecycleOwner = this@PostsActivity
        }

        initialize()
    }

    private fun initialize() {
        binding.postsRecyclerView.apply {
            adapter = PostsAdapter()
            addItemDecoration(MarginItemDecoration(8.dp))
        }
        observeValues()
        postsViewModel.takePosts()
    }

    private fun observeValues() {
        postsViewModel.postResult.observe(this) {
            (binding.postsRecyclerView.adapter as PostsAdapter).addAll(it)
        }
    }
}