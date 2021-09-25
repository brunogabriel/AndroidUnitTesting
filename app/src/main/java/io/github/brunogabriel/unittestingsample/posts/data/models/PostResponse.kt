package io.github.brunogabriel.unittestingsample.posts.data.models

data class PostResponse(
    val id: Long,
    val userId: Long,
    val title: String,
    val body: String
)